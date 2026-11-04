package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class BasePage {

    static final String pageUrl = "/admin/users";
    static final SelenideElement buttonCreate = $("[data-testid='AddIcon']");
    static final SelenideElement fieldLastName = $("[name='lastName']");
    static final SelenideElement fieldFirstName = $("[name='firstName']");
    static final SelenideElement fieldLogin = $("[name='login']");
    static final SelenideElement fieldEmail = $("[name='email']");
    static final SelenideElement fieldPhone = $("[placeholder='+7 __________']");
    static final SelenideElement fieldStatus = $("[data-value='ENABLED']");
    static final SelenideElement organizationField = $("input[role='combobox']");
    static final SelenideElement buttonSave = $(byText("Сохранить"));
    static final SelenideElement userRows = $(".host-1cdcmys-MUIDataTable-responsiveBase");
    static final SelenideElement statusSelectionButton = $(byText("Ожидает активации"));
}
