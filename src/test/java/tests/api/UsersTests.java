package tests.api;

import api.ApiUser;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ValidatableResponse;
import models.auth.AuthRequestBodyModel;
import models.auth.AuthResponseModel;
import models.users.ActionResponse;
import models.users.UserCreateRequestModel;
import models.users.UserCreateRequestWithInvalidLoginModel;
import models.users.UserCreateResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.TestDataGenerator;

import java.io.File;
import java.util.List;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;
import static tests.api.DataTest.*;

public class UsersTests extends BaseAPITest {
    File avatarFile = new File("src/test/resources/files/foto.jpg");
    private String token;

    @Test
    @DisplayName("Успешное создание пользователя")
    public void successfulUserCreationTest() {
        AuthRequestBodyModel loginData = AuthRequestBodyModel.builder()
                .username("Lk.kamotive")
                .password("v6x7gM2m5Yu8!")
                .realm("kamotive")
                .build();

        AuthResponseModel authResponse =
                api.auth.login(loginData);
        token = authResponse.getAccessToken();
        step("Создание пользователя через API", () -> {
            UserCreateRequestModel newUser = TestDataGenerator.generateValidUser();
            UserCreateResponseModel userCreateResponse = api.user.successfulCreationUser(newUser, token);

            step("Проверка созданного пользователя", () -> {
                String createdArtifactId = userCreateResponse.getMetadata().getArtifactId();
                assertThat(userCreateResponse.getSuccess()).isTrue();
                assertThat(userCreateResponse.getErrorCode()).isNull();
                assertThat(createdArtifactId).isNotBlank();
                assertThat(createdArtifactId).hasSize(36);
            });
        });
    }

    @Test
    @DisplayName("Фильтрация списка пользователей по организации и проверка структуры ответа")
    public void shouldFilterUsersByOrganizationAndVerifyListStructureTest() {
        AuthRequestBodyModel loginData = AuthRequestBodyModel.builder()
                .username("Lk.kamotive")
                .password("v6x7gM2m5Yu8!")
                .realm("kamotive")
                .build();
        AuthResponseModel authResponse =
                api.auth.login(loginData);
        token = authResponse.getAccessToken();

        ValidatableResponse response =
                step("Запрос списка пользователей с фильтром по организации", () -> ApiUser.getUsersByOrganization(ORG_ID, token));

        JsonPath json = response.extract().jsonPath();

        List<?> usersList = json.getList("_embedded.userDtoes");
        assertThat(usersList)
                .as("Список пользователей организации не должен быть пустым")
                .isNotEmpty();

        List<String> orgIdsInResponse = json.getList("_embedded.userDtoes.organization.id", String.class);
        assertThat(orgIdsInResponse)
                .as("ВСЕ пользователи в ответе должны принадлежать организации ПАО КАМАЗ")
                .allMatch(orgId -> orgId.equals(orgId));

        assertThat(json.getString("_embedded.userDtoes[0].id")).isNotNull();
        assertThat(json.getString("_embedded.userDtoes[0].login")).isNotNull();
        assertThat(json.getString("_embedded.userDtoes[0].email")).isNotNull();

        assertThat(json.getInt("page.size"))
                .as("Размер страницы (size) должен быть 20").isEqualTo(20);

        assertThat(json.getInt("page.number"))
                .as("Номер текущей страницы должен быть 0").isEqualTo(0);

        assertThat(json.getInt("page.totalElements"))
                .as("Общее количество элементов должно быть больше 0").isGreaterThan(0);

        assertThat(json.getInt("page.totalPages"))
                .as("Общее количество страниц должно быть больше 0").isGreaterThan(0);
    }


    @Test
    @DisplayName("Ошибка валидации при вводе логина русскими буквами")
    public void errorUserCreationTest() {
        AuthRequestBodyModel loginData = AuthRequestBodyModel.builder()
                .username("Lk.kamotive")
                .password("v6x7gM2m5Yu8!")
                .realm("kamotive")
                .build();

        AuthResponseModel authResponse =
                api.auth.login(loginData);
        token = authResponse.getAccessToken();
        step("Создание пользователя через API", () -> {
            UserCreateRequestModel invalidUserLogin = TestDataGenerator.generateUserInvalidLogin();


            UserCreateRequestWithInvalidLoginModel userCreateRequestWithInvalidLogin = api.user.fillingInTheLoginFieldWithInvalidData(invalidUserLogin, token);
            step("Проверка кода ошибки 404 и текста сообщения", () -> {
                assertThat(userCreateRequestWithInvalidLogin.getStatus())
                        .as("Status должен быть 422")
                        .isNotNull()
                        .isEqualTo(422);
                assertThat(userCreateRequestWithInvalidLogin.getMessage()).contains(ERROR_MESSAGE);
                assertThat(userCreateRequestWithInvalidLogin.getErrors().get(0).getField()).isEqualTo(ERROR_FIELD);
                assertThat(userCreateRequestWithInvalidLogin.getErrors().get(0).getMessage()).contains(ERROR_422_MESSAGE);
            });
        });
    }

    @Test
    @DisplayName("Успешная активация пользователя")
    public void uccessfulUserActivationTest() {
        AuthRequestBodyModel loginData = AuthRequestBodyModel.builder()
                .username("Lk.kamotive")
                .password("v6x7gM2m5Yu8!")
                .realm("kamotive")
                .build();

        AuthResponseModel authResponse =
                api.auth.login(loginData);
        token = authResponse.getAccessToken();
        UserCreateRequestModel newUser = TestDataGenerator.generateValidUser();

        step("Создание пользователя через API", () -> {
            UserCreateResponseModel userCreateResponse = api.user.successfulCreationUser(newUser, token);
            ActionResponse action = step("Активация пользователя через API", () ->
                    api.user.userActivation(userCreateResponse.getMetadata().getArtifactId(), ACTIVE_STATUS_ID, token));
            step("Проверка успешного ответа от сервера при активации", () -> {
                assertThat(action.getSuccess()).isTrue();
                assertThat(action.getErrorCode()).isNull();
                assertThat(action.getMetadata().getArtifactId()).isEqualTo("OK");
            });
        });
    }

    @Test
    @DisplayName("Добавление аватара пользоателю")
    public void avatarTest() {
        AuthRequestBodyModel loginData = AuthRequestBodyModel.builder()
                .username("Lk.kamotive")
                .password("v6x7gM2m5Yu8!")
                .realm("kamotive")
                .build();

        AuthResponseModel authResponse =
                api.auth.login(loginData);
        token = authResponse.getAccessToken();
        UserCreateRequestModel newUser = TestDataGenerator.generateValidUser();

        step("Создание пользователя через API", () -> {
            UserCreateResponseModel userCreateResponse = api.user.successfulCreationUser(newUser, token);
            step("Активация пользователя через API", () ->
                    api.user.userActivation(userCreateResponse.getMetadata().getArtifactId(), ACTIVE_STATUS_ID, token));
            ValidatableResponse response = step("Добавить аватар пользователю через API", () ->
                    ApiUser.uploadAvatar(userCreateResponse.getMetadata().getArtifactId(), avatarFile, token));
            step("Проверка статус кода 200", () ->
                    response.statusCode(200));
        });
    }

    @Test
    @DisplayName("Удаление аватара у пользователя")
    public void deleteAvatarTest() {
        AuthRequestBodyModel loginData = AuthRequestBodyModel.builder()
                .username("Lk.kamotive")
                .password("v6x7gM2m5Yu8!")
                .realm("kamotive")
                .build();

        AuthResponseModel authResponse =
                api.auth.login(loginData);
        token = authResponse.getAccessToken();
        UserCreateRequestModel newUser = TestDataGenerator.generateValidUser();

        step("Создание пользователя через API", () -> {
            UserCreateResponseModel userCreateResponse = api.user.successfulCreationUser(newUser, token);
            step("Активация пользователя через API", () ->
                    api.user.userActivation(userCreateResponse.getMetadata().getArtifactId(), ACTIVE_STATUS_ID, token));
            ValidatableResponse response = step("Добавить аватар пользователю через API", () ->
                    ApiUser.uploadAvatar(userCreateResponse.getMetadata().getArtifactId(), avatarFile, token));
            ValidatableResponse responseDelete = step("Удаление аватара пользователя через API", () ->
                    ApiUser.deleteAvatar(userCreateResponse.getMetadata().getArtifactId(), token));
            step("Проверка успешного удаления аватара статус код 200", () ->
                    responseDelete.statusCode(200));
        });
    }

    @Test
    @DisplayName("Успешное удаление пользователя")
    public void uccessfulDeleteUserTest() {
        AuthRequestBodyModel loginData = AuthRequestBodyModel.builder()
                .username("Lk.kamotive")
                .password("v6x7gM2m5Yu8!")
                .realm("kamotive")
                .build();

        AuthResponseModel authResponse =
                api.auth.login(loginData);
        token = authResponse.getAccessToken();

        step("Создание пользователя через API", () -> {
            UserCreateRequestModel newUser = TestDataGenerator.generateValidUser();
            UserCreateResponseModel userCreateResponse = api.user.successfulCreationUser(newUser, token);
            ActionResponse action = step("Удаление пользователя через API", () ->
                    api.user.deleteUser(userCreateResponse.getMetadata().getArtifactId(), token));
            step("Проверка успешного ответа от сервера при удалении пользователя", () -> {
                assertThat(action.getSuccess()).isTrue();
                assertThat(action.getErrorCode()).isNull();
                assertThat(action.getErrorMessage()).isNull();
            });
        });
    }

    @Test
    @DisplayName("Ошибка при удалении не существующего пользователя")
    public void errorDeleteUserTest() {
        AuthRequestBodyModel loginData = AuthRequestBodyModel.builder()
                .username("Lk.kamotive")
                .password("v6x7gM2m5Yu8!")
                .realm("kamotive")
                .build();

        AuthResponseModel authResponse =
                api.auth.login(loginData);
        token = authResponse.getAccessToken();
        UserCreateRequestWithInvalidLoginModel errorDelelteUser = step("Удаление пользователя через API", () ->
                api.user.errorDeleteUser(INVALID_USER_ID, token));
        step("Проверка ответа от сервера при удалении  не существующего пользователя и проверка текста ошибки", () -> {
            step("Проверка кода ошибки 409 и текста сообщения", () -> {
                assertThat(errorDelelteUser.getStatus())
                        .as("Status должен быть 404")
                        .isNotNull()
                        .isEqualTo(404);
                assertThat(errorDelelteUser.getMessage())
                        .as("Сообщение должно содержать ошибку 404 и ид пользователя")
                        .contains(ERROR_404_MESSAGE);
            });
        });
    }
}
