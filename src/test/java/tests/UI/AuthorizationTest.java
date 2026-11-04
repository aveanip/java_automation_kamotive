package tests.UI;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.AuthorizationPage;
import pages.HomePage;

import static io.qameta.allure.Allure.step;
import static tests.UI.TestData.*;

public class AuthorizationTest extends BaseUITest {

    @Test
    @DisplayName("Проверка авторизации валидными данными")
    public void successfulAuthorizationWithValidDataTest() {
        step("Открыть страницу авторизации и проверить заголовок", () -> {
            authorizationPage
                    .openPage()
                    .checkTitleText(title);
        });
        step("Ввести валидные данные и выполнить вход", () -> {
            authorizationPage
                    .login(login, password)
                    .checkHomePageIsOpened();
        });
        step("Проверить отображение и текст плиток меню на главной странице", () -> {
            home
                    .openPage()
                    .checkHomePageIsOpened()
                    .checkAllMenuTilesVisible()
                    .checkMenuTilesText();
        });
    }

    @Test
    @DisplayName("Проверка текста ошибки при неверном логине или пароле")
    void wrongCredentialsAuthorization() {
        authorizationPage.openPage()
                .login(invalidLogin, invalidPassword);
        authorizationPage.checkAuthoruzationPageIsOpened()
                .checkErrorMessage(errorText);
    }

}
