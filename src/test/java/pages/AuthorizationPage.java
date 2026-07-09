package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;

public class AuthorizationPage extends BasePage{

    private String pageUrl = "/sign-in/kamotive";
    private final SelenideElement
            loginFromTitle = $$("h3").first(),
            authorizationForm = $(".host-dxql6n-form"),
            userNameInput = $("input[name='username']"),
            passswordNameInput = $("input[type='password']"),
            signInButton = $(".host-1ctxyfa-buttons"),
            errorMessage = $(".host-1m94vb6-errorMessage");

    public AuthorizationPage openPage() {
        open(pageUrl);
        checkAuthoruzationPageIsOpened();
        return this;
    }

    public AuthorizationPage checkAuthoruzationPageIsOpened() {
        authorizationForm.shouldBe(visible);

        return this;
    }

//    public AuthorizationPage checkPageDisappeared() {
//        webdriver().shouldNotHave(urlContaining(pageUrl));
//        authorizationForm.should(disappear);
//        return this;
//    }

    public AuthorizationPage checkTitleText(String value) {
        loginFromTitle.shouldHave(text(value));

        return this;
    }

    public AuthorizationPage typeuserName(String value) {
        userNameInput.setValue(value);

        return this;
    }

    public AuthorizationPage typepassword(String value) {
        passswordNameInput.setValue(value);

        return this;
    }

    public HomePage login (String userName, String password){
        typeuserName(userName);
        typepassword(password);
        clickButton();

        return new HomePage();
    }


    public AuthorizationPage clickButton() {
        signInButton.click();

        return this;
    }

    public AuthorizationPage checkErrorMessage(String message) {
        errorMessage.shouldHave(text(message));

        return this;
    }

}
