package tests.ui;

import io.qameta.allure.Description;
import org.junit.jupiter.api.Test;
import static io.qameta.allure.Allure.step;
import static tests.ui.TestData.*;

public class HomePageTests extends BaseUITest{
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
