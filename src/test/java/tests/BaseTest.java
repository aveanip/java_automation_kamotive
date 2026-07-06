package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class BaseTest {

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
