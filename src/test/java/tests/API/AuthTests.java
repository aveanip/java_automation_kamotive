package tests.API;

import Api.ApiClient;
import models.Auth.AuthRequestBodyModel;
import models.Auth.AuthResponseModel;
import models.Auth.ErrorResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;
import static tests.API.DataTest.expectedTokenPath;

public class AuthTests extends BaseAPITest {

    ApiClient api = new ApiClient();

    @Test
    @DisplayName("Успешная авторизация с валидными данными")
    public void successfulLoginTest() {
        AuthRequestBodyModel loginData = AuthRequestBodyModel.builder()
                .username("ntstin125")
                .password("ztsT1UfMwa6GWsi2)VztsTUfMwa6GWsi2)V1")
                .realm("kamotive")
                .build();


        AuthResponseModel loginResponse = api.auth.login(loginData);

        step("Проверка Refresh и Access токенов", () -> {
            String actualRefresh = loginResponse.getRefreshToken();
            String actualAccess = loginResponse.getAccessToken();
            assertThat(actualRefresh)
                    .as("Refresh токен не должен быть null")
                    .isNotNull()
                    .startsWith(expectedTokenPath); // обычно "eyJ"

            assertThat(actualAccess)
                    .as("Access токен не должен быть null")
                    .isNotNull()
                    .startsWith(expectedTokenPath);

            // Проверяем тип токена
            assertThat(loginResponse.getTokenType())
                    .as("Тип токена должен быть Bearer")
                    .isEqualTo("Bearer");

            // Проверяем, что это действительно два разных токена
            assertThat(actualRefresh).isNotEqualTo(actualAccess);
        });
    }

    @Test
    @DisplayName("Вход с невалидным данными")
    public void loginWithInvalidDataTest() {
        AuthRequestBodyModel loginData = AuthRequestBodyModel.builder()
                .username("ntstin12")
                .password("ztsT1UfMwa6GWsi2)VztsTUfMwa6GWsi2)V")
                .realm("kamotive")
                .build();

        ErrorResponseModel errorResponseModel = api.auth.invalidLogin(loginData);

        step("Проверка Status и Message", () -> {
            assertThat(errorResponseModel.getStatus())
                    .as("Status должен быть 404")
                    .isNotNull()
                    .isEqualTo(404);

            assertThat(errorResponseModel.getMessage())
                    .as("Сообщение должно указывать на то, что пользователь не найден")
                    .isEqualTo("404 NOT_FOUND \"Пользователь ntstin12 не найден\"");
        });
    }
}
