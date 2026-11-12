package tests.ui;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import pages.AuthorizationPage;
import pages.HomePage;
import java.util.List;
import java.util.Map;

import static com.codeborne.selenide.Selenide.*;

public class BaseUITest {
    AuthorizationPage authorizationPage = new AuthorizationPage();
    HomePage home = new HomePage();

    @BeforeAll
    public static void setUpUi() {
        // Настройки для Selenide (UI)
        Configuration.baseUrl = System.getProperty("baseUrl", "https://kamotive.ru");
        Configuration.browser = System.getProperty("BROWSER", "chrome");
        Configuration.browserSize = System.getProperty("BROWSER_SIZE", "1920x1080");
        Configuration.browserVersion = System.getProperty("BROWSER_VERSION", ""); // Пустая строка = последняя версия
        Configuration.headless = Boolean.parseBoolean(System.getProperty("HEADLESS", "false"));


        RestAssured.baseURI = System.getProperty("api.url", Configuration.baseUrl);
        String remote = System.getProperty("REMOTE");
        if (remote != null && !remote.isEmpty()) {
            Configuration.remote = remote;
            DesiredCapabilities capabilities = new DesiredCapabilities();
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments(List.of("--disable-dev-shm-usage", "--no-sandbox"));
            capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
            capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                    "enableVNC", true,
                    "enableVideo", true,
                    "enableLog", true
            ));
            Configuration.browserCapabilities = capabilities;
        }

        System.out.println("URL: " + Configuration.baseUrl);
        System.out.println("Browser: " + Configuration.browser);
        System.out.println("Browser size: " + Configuration.browserSize);
        System.out.println("Remote: " + Configuration.remote);
        System.out.println("API URL: " + RestAssured.baseURI); // <-- 4. ДОБАВИТЬ для проверки в консоли
    }

    @BeforeEach
    public void setUp() {
                open("/");
                executeJavaScript("localStorage.setItem('i18nextLng', 'ru');");
                Selenide.closeWebDriver();

        SelenideLogger.addListener("allure", new AllureSelenide()
                .screenshots(true)
                .savePageSource(false));
            }

    @AfterEach
    void tearDown() {
        addAttachments();
        closeWebDriver();
    }

    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
        Attach.getVideoUrl();
    }
}

