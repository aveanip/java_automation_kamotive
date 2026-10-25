package tests.UI;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.AuthorizationPage;
import tests.utils.BaseUITest;

import static tests.UI.TestData.*;

public class AuthorizationTest extends BaseUITest {

    AuthorizationPage authorizationPage = new AuthorizationPage();

    @Test
    @DisplayName("Проверка заголовка страницы авторизации")
    void verifyAuthorizationPageHeaderText() {
        authorizationPage
                .openPage()
                .checkTitleText(title);
    }

    @Test
    @DisplayName("Проверка авторизации валидными данными")
    public void successfulAuthorizationWithValidDataTest() {
        authorizationPage
                .openPage()
                .checkTitleText(title)
                .login(login, password)
                .checkHomePageIsOpened()
                .header.checkFullName(fullUserName);
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
