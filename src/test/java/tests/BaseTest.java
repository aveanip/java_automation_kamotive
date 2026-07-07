package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import pages.AuthorizationPage;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static tests.testsdata.TestData.*;

public class BaseTest {



    @BeforeEach
    public void setUp() {

        AuthorizationPage authorizationPage = new AuthorizationPage();
       authorizationPage.openPage()
                .typeuserName(login)
                .typepassword(password)
                .clickButton()
               .checkUserName(fullUserName);
    }

        @BeforeAll
        static void beforeAll() {
            Configuration.baseUrl = "https://test.kamotive.ru";
            Configuration.browser = "chrome";
            Configuration.browserSize = "1920x1080";
        }

        @AfterEach
        void afterEach() {

            closeWebDriver();
        }
}
