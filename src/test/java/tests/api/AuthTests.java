package tests.api;

import models.auth.AuthRequestBodyModel;
import models.auth.AuthResponseModel;
import models.auth.ErrorResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;
import static tests.api.DataTest.EXPECTED_TOKEN_PATH;

public class AuthTests extends BaseAPITest {

    @Test
    @DisplayName("Успешная авторизация с валидными данными")
    public void successfulLoginTest() {
        AuthRequestBodyModel loginData = AuthRequestBodyModel.builder()
                .username("Lk.kamotive")
                .password("v6x7gM2m5Yu8!")
                .realm("kamotive")
                .build();

        AuthResponseModel loginResponse = api.auth.login(loginData);

        step("Проверка Refresh и Access токенов", () -> {
            String actualRefresh = loginResponse.getRefreshToken();
            String actualAccess = loginResponse.getAccessToken();
            assertThat(actualRefresh)
                    .as("Refresh токен не должен быть null")
                    .isNotNull()
                    .startsWith(EXPECTED_TOKEN_PATH); // обычно "eyJ"

            assertThat(actualAccess)
                    .as("Access токен не должен быть null")
                    .isNotNull()
                    .startsWith(EXPECTED_TOKEN_PATH);

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
                .username("lfuffdjfddg")
                .password("v6x7gM2m5Yu8!1")
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
                    .isEqualTo("404 NOT_FOUND \"Пользователь lfuffdjfddg не найден\"");
        });
    }
}
