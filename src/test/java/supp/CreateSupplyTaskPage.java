package supp;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class CreateSupplyTaskPage {

    private final SelenideElement taskNameInptut =
            $x("//label[contains(text(), 'Название задания')]/following-sibling::div//input");

    private final SelenideElement taskDescriptionTextarea = $("textarea[placeholder='Введите текст описания']");
    private final SelenideElement saveButton = $(byText("Сохранить"));


    public CreateSupplyTaskPage openPage() {
        open("/addNewSupply");

        return this;
    }


    public CreateSupplyTaskPage taskName(String value) {
        taskNameInptut.click();
        taskNameInptut.setValue(value);

        return this;
    }

    public CreateSupplyTaskPage taskDescription(String value) {
        taskDescriptionTextarea.setValue(value);

        return this;
    }

    public CreateSupplyTaskPage clickSaveButton() {
        saveButton.click();

        return this;
    }
}
