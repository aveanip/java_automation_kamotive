package tests.ui;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.qameta.allure.Allure.step;
import static tests.ui.TestData.*;

public class AuthorizationTest extends BaseUITest {
    @Test
    @DisplayName("Проверка авторизации валидными данными")
    public void successfulAuthorizationWithValidDataTest() {
            authorizationPage
                    .openPage();
        authorizationPage
                    .checkTitleText(TITLE)
                    .login(LOGIN, VALID_PASSWORD)
                    .checkHomePageIsOpened();
            home
                    .openPage()
                    .checkHomePageIsOpened()
                    .checkAllMenuTilesVisible()
                    .checkMenuTilesText();
    }

    @Test
    @DisplayName("Проверка текста ошибки при неверном логине или пароле")
    void wrongCredentialsAuthorization() {
        authorizationPage
                .openPage();
        authorizationPage
                .login(INVALID_LOGIN, INVALID_PASSWORD);
        authorizationPage.checkAuthoruzationPageIsOpened()
                .checkErrorMessage(ERROR_TEXT);
    }

}
