package tests.UI;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Description;
import io.restassured.http.Cookie;
import io.restassured.response.Response;
import models.Auth.AuthRequestBodyModel;
import models.Auth.AuthResponseModel;
import org.junit.jupiter.api.Test;
import pages.HomePage;

import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.qameta.allure.Allure.step;
import static tests.UI.TestData.*;

public class HomePageTests extends BaseUITest{


    protected String token;

    @Test
    @Description("Проверка отображения меню на главной странице после авторизации (UI)")
    public void testMenuDisplayedAfterLogin() {
        step("[UI] Авторизация в системе Kamotive и получение токена", () -> {
            authorizationPage
                    .openPage()
                    .login(login, password)
                    .checkHomePageIsOpened();

            step("[UI] Проверка меню", () -> {
                home.openPage()
                        .checkHomePageIsOpened()
                        .checkAllMenuTilesVisible()
                        .checkMenuTilesText();
            });
        });
    }
}
