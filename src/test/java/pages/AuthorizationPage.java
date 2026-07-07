package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class AuthorizationPage {

    private final SelenideElement userNameInput = $("input[name='username']");
    private final SelenideElement passswordNameInput = $("input[type='password']");
    private final SelenideElement signInButton = $(".host-1ctxyfa-buttons");
    private final SelenideElement fullUserName =
            $(".MuiTypography-root.MuiTypography-h5.host-ytiac7-fullNameTypography");

    public AuthorizationPage openPage() {
        open("/sign-in/kamotive");

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

    public AuthorizationPage clickButton() {
        signInButton.click();

        return this;
    }

    public AuthorizationPage checkUserName(String value) {
        fullUserName.shouldBe(Condition.text(value));

        return this;
    }
}
