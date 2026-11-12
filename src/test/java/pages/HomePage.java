package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import java.util.List;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;

public class HomePage  {
    private String pageUrl = "/index";
    private SelenideElement menuPageContainer = $(".host-q6svxr-root");
    private ElementsCollection menuTiles = $$(".host-1b4i6d1-tileButton");

    @Step("Открытие главной страницы")
    public HomePage openPage() {
        open(pageUrl);
        checkHomePageIsOpened();

        return this;
    }

    @Step("Проверка что главная страница открыта")
    public HomePage checkHomePageIsOpened() {
        webdriver().shouldHave(urlContaining(pageUrl));
        menuPageContainer.shouldBe(visible);
        return this;
    }

    @Step("Проверка что все плитки меню видимы")
    public HomePage checkAllMenuTilesVisible() {
        menuTiles.shouldHave(sizeGreaterThan(0));
        return this;
    }

    @Step("Проверка названий пунктов меню")
    public HomePage checkMenuTilesText() {
        List<String> expectedMenuItems = List.of(
                "Профиль",
                "Моя активность",
                "Администрирование",
                "Поставка",
                "Аудит",
                "Управление временем",
                "Рабочие процессы",
                "Документы",
                "Обратная связь"
        );
        menuTiles.shouldHave(texts(expectedMenuItems));
        return this;
    }
}
