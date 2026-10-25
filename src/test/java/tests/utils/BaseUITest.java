package tests.utils;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import pages.AuthorizationPage;
import pages.HomePage;
import supp.SupplyPage;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static tests.UI.TestData.*;

public class BaseUITest {
    AuthorizationPage authorizationPage = new AuthorizationPage();
    HomePage homePage = new HomePage();
    SupplyPage supplyPage = new SupplyPage();

    @BeforeEach
    public void setUp() {
            authorizationPage
                    .openPage()
                    .checkTitleText(title)
                    .login(login, password)
                    .checkHomePageIsOpened()
                    .header.checkFullName(fullUserName);
    }

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = baseUrl;

        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
    }

    @AfterEach
    void afterEach() {

        closeWebDriver();
    }
}
