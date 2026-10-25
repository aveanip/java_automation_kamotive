package tests.API;

import Api.ApiClient;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import models.Auth.AuthRequestBodyModel;
import models.Auth.AuthResponseModel;
import models.OrganizationModel;
import models.Spaces.ErrorWhenCreatingTheSpaceModel;
import models.Spaces.SpaceStatusModel;
import models.Spaces.SpacesRequestBodyModel;
import models.Spaces.SpacesResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Locale;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

public class SpacesTests extends BaseAPITest {
    private String token;
    private final Faker faker = new Faker(new Locale("ru"));
    ApiClient api = new ApiClient();
    String uniqueId = String.valueOf(System.currentTimeMillis());
    String name = "тестовое пространство" + "_" + uniqueId;
    String description = faker.lorem().sentence();
    String message = "409 CONFLICT \"Пространство с именем " + name + " для организации ПАО \"КАМАЗ\" уже существует\"";
    OrganizationModel org;
    SpaceStatusModel status;
    String userID = "2a00725b-3659-4313-899e-f7847e21ad5a";
    String invalidUserID = "31e9a457-43e7-45c0-8f04-555a83acd1de";
    String expectedName = "тест тест тест";
    String expectedRole = "READ";


    @Test
    @DisplayName("Успешное создание пространства")
    public void successfulCreationOfSpaceTest() {


        AuthRequestBodyModel loginData = AuthRequestBodyModel.builder()
                .username("ntstin125")
                .password("ztsT1UfMwa6GWsi2)VztsTUfMwa6GWsi2)V1")
                .realm("kamotive")
                .build();

        AuthResponseModel authResponse =
                api.auth.login(loginData);
        token = authResponse.getAccessToken();


        org = OrganizationModel.builder()
                .id("f441f4d4-43ff-4969-b751-17fcfb37f20f")
                .name("ПАО \"КАМАЗ\"")
                .description(null)
                .build();

        status = SpaceStatusModel.builder()
                .name("Active")
                .build();

        step("Создание пространства через API", () -> {
            SpacesRequestBodyModel spaceData = SpacesRequestBodyModel.builder()
                    .name(name)
                    .description(description)
                    .iconName("")
                    .organization(org)
                    .status(status)
                    .build();
            SpacesResponseModel spacesResponse = api.spaces.successfulCreationOfSpace(spaceData, token);
                step("Проверка корректности созданного пространства", () -> {
                assertThat(spacesResponse.getId()).isNotNull();
                assertThat(spacesResponse.getName()).isEqualTo(name);
                assertThat(spacesResponse.getDescription()).isEqualTo(description);
                assertThat(spacesResponse.getOrganization()).isNotNull();
                assertThat(spacesResponse.getOrganization().getId()).isEqualTo(org.getId());
                assertThat(spacesResponse.getStatus()).isNotNull();
                assertThat(spacesResponse.getStatus().getName()).isEqualTo("Active");
                assertThat(spacesResponse.getUpdateDate()).isNull();
                assertThat(spacesResponse.getCreateDate()).isNotNull();
            });
        });
    }

    @Test
    @DisplayName("Ошибка при создании пространства с дублирующимся названием")
    public void createSpaceWithDuplicateNameTest() {

        String uniqueName = "тестовое пространство" + "_" + uniqueId;
        String uniqueDescription = faker.lorem().sentence();

        AuthRequestBodyModel loginData = step("Авторизация",
                () -> AuthRequestBodyModel.builder()
                        .username("ntstin125")
                        .password("ztsT1UfMwa6GWsi2)VztsTUfMwa6GWsi2)V1")
                        .realm("kamotive")
                        .build());

        AuthResponseModel authResponse = api.auth.login(loginData);
        token = authResponse.getAccessToken();

        org = OrganizationModel.builder()
                .id("f441f4d4-43ff-4969-b751-17fcfb37f20f")
                .name("ПАО \"КАМАЗ\"")
                .description(null)
                .build();

        status = SpaceStatusModel.builder()
                .name("Active")
                .build();

        SpacesRequestBodyModel spaceData = SpacesRequestBodyModel.builder()
                .name(uniqueName)
                .description(uniqueDescription)
                .iconName("")
                .organization(org)
                .status(status)
                .build();
        step("Успешное первое создание пространства", () -> {
            api.spaces.successfulCreationOfSpace(spaceData, token);
        });

        SpacesRequestBodyModel duplicateSpaceData = SpacesRequestBodyModel.builder()
                .name(name)
                .description(description)
                .iconName("")
                .organization(org)
                .status(status)
                .build();

        ErrorWhenCreatingTheSpaceModel errorWhenCreatingTheSpace =
                step("Попытка создания пространства с дублирующимся названием", () ->
                        api.spaces.duplicateSpaceNameValidation(duplicateSpaceData, token));

        step("Проверка кода ошибки 409 и текста сообщения", () -> {
            assertThat(errorWhenCreatingTheSpace.getStatus())
                    .as("Status должен быть 404")
                    .isNotNull()
                    .isEqualTo(409);
            assertThat(errorWhenCreatingTheSpace.getMessage())
                    .isEqualTo(message);
        });
    }

    @Test
    @DisplayName("Успешное добавление пользователя в пространство и проверка роли по умолчанию")
    public void addingAUserToTheCreatedSpaceTest() {

        AuthRequestBodyModel loginData = AuthRequestBodyModel.builder()
                .username("ntstin125")
                .password("ztsT1UfMwa6GWsi2)VztsTUfMwa6GWsi2)V1")
                .realm("kamotive")
                .build();

        AuthResponseModel authResponse =
                api.auth.login(loginData);
        token = authResponse.getAccessToken();


        org = OrganizationModel.builder()
                .id("f441f4d4-43ff-4969-b751-17fcfb37f20f")
                .name("ПАО \"КАМАЗ\"")
                .description(null)
                .build();

        status = SpaceStatusModel.builder()
                .name("Active")
                .build();

        step("Создание пространства через API", () -> {
            SpacesRequestBodyModel spaceData = SpacesRequestBodyModel.builder()
                    .name(name)
                    .description(description)
                    .iconName("")
                    .organization(org)
                    .status(status)
                    .build();
            SpacesResponseModel spacesResponse = api.spaces.successfulCreationOfSpace(spaceData, token);

            step("Добавление пользователя в пространство и проверяем его в списке", () -> {
                api.spaces.successfulUserAdd(spacesResponse.getId(),userID, token);
            });

            Response usersResponse = step("Получение списка пользователей", () ->
                    api.spaces.getUsersInSpace(spacesResponse.getId(), token)
            );
            step("Проверка данных добавленного пользователя в списке", () -> {
            List<String> userIds = usersResponse.jsonPath().getList("_embedded.spaceMembers.reference.id");

            assertThat(userIds)
                    .as("ID пользователя должен присутствовать в списке")
                    .isNotNull()
                    .contains(userID);

            List<String> userNames = usersResponse.jsonPath().getList("_embedded.spaceMembers.reference.fullName");

            assertThat(userNames)
                    .as("Полное имя пользователя должно присутствовать в списке")
                    .isNotNull()
                    .contains(expectedName);

            List<String> roles = usersResponse.jsonPath().getList("_embedded.spaceMembers.access.name");

            assertThat(roles)
                    .as("Роль пользователя должна присутствовать в списке")
                    .isNotNull()
                    .contains(expectedRole);
        });
        });
        }
    }
