package pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;

public class HomePage extends BasePage{
private String pageUrl = "/index";

    private SelenideElement menuPageContainer = $ (".host-q6svxr-root");

    public HomePage openPage(){
        open(pageUrl);
        checkHomePageIsOpened();

        return this;
    }

    public HomePage checkHomePageIsOpened(){
        webdriver().shouldNotHave(urlContaining(pageUrl));
        menuPageContainer.shouldBe(visible);

        return this;
    }

}
