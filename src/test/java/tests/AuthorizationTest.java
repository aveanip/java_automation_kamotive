package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.AuthorizationPage;

import static tests.testsdata.TestData.*;

public class AuthorizationTest extends BaseTest {
    AuthorizationPage authorizationPage = new AuthorizationPage();

    @Test
    @DisplayName("Проверка авторизации валидными данными")

    void successfulAuthorizationWithValidDataTest(){
        authorizationPage.openPage()
                .typeuserName(login)
                .typepassword(password)
                .clickButton()
                .checkUserName(fullUserName);

    }
}
