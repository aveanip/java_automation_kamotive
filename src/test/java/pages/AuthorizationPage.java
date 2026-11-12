package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;

public class AuthorizationPage {

    private String pageUrl = "/sign-in/kamotive";
    private final SelenideElement
            loginFromTitle = $$("h3").first(),
            authorizationForm = $(".host-dxql6n-form"),
            userNameInput = $("input[name='username']"),
            passswordNameInput = $("input[type='password']"),
            signInButton = $(".host-1ctxyfa-buttons"),
            errorMessage = $(".host-1m94vb6-errorMessage");
    private final SelenideElement pageTitle = $("h3.MuiTypography-h3");

    @Step("Открытие страницы авторизации")
    public AuthorizationPage openPage() {
        open("/sign-in/kamotive");
        ensureRussianLanguage();
        return this;
    }
    private void ensureRussianLanguage() {
        pageTitle.shouldBe(visible, Duration.ofSeconds(10));
        if (pageTitle.text().contains("Login to Kamotive portal")) {
            $$(By.xpath("//*[text()='RU']")).first()
                    .shouldBe(visible)
                    .click();
            pageTitle.shouldHave(text("Вход в портал Камотив"));
        }
    }

//    @Step("Открытие страницы авторизации")
//    public AuthorizationPage openPage() {
//        open(pageUrl);
//        checkAuthoruzationPageIsOpened();
//        return this;
//    }
    @Step("Проверка, что страница авторизации открыта")
    public AuthorizationPage checkAuthoruzationPageIsOpened() {
        webdriver().shouldHave(url(baseUrl + pageUrl));
        authorizationForm.shouldBe(visible);
        return this;
    }
    @Step("Проверка заголовка: '{value}'")
    public AuthorizationPage checkTitleText(String value) {
        loginFromTitle.shouldHave(text(value));
        return this;
    }
    @Step("Ввод логина пользователя: '{value}'")
    public AuthorizationPage typeuserName(String value) {
        userNameInput.setValue(value);
        return this;
    }
    @Step("Ввод пароля")
    public AuthorizationPage typepassword(String value) {
        passswordNameInput.setValue(value);
        return this;
    }
    @Step("Выполнение входа пользователя '{userName}'")
    public HomePage login (String userName, String password){
        typeuserName(userName);
        typepassword(password);
        clickButton();
        return new HomePage();
    }

    @Step("Нажатие на кнопку входа")
    public AuthorizationPage clickButton() {
        signInButton.click();
        return this;
    }
    @Step("Проверка сообщения об ошибке: '{message}'")
    public AuthorizationPage checkErrorMessage(String message) {
        errorMessage.shouldHave(text(message));
        return this;
    }

}
