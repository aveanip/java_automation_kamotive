package pages.components;

import com.codeborne.selenide.SelenideElement;
import pages.HomePage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class HeaderComponent {

   private SelenideElement fullName = $(".MuiTypography-root.MuiTypography-h5.host-ytiac7-fullNameTypography");

    public HeaderComponent checkFullName(String value) {
        fullName.shouldBe(text(value));

        return this;
    }




}
