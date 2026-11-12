package tests.ui;

import io.qameta.allure.Description;
import org.junit.jupiter.api.Test;

import static tests.ui.TestData.LOGIN;
import static tests.ui.TestData.VALID_PASSWORD;

public class HomePageTests extends BaseUITest {
    @Test
    @Description("Проверка отображения меню на главной странице после авторизации (UI)")
    public void testMenuDisplayedAfterLogin() {
        authorizationPage
                .openPage();
        authorizationPage
                .login(LOGIN, VALID_PASSWORD)
                .checkHomePageIsOpened();
        home.openPage()
                .checkHomePageIsOpened()
                .checkAllMenuTilesVisible();
    }
}
