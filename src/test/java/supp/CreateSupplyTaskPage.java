package supp;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.cssValue;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class CreateSupplyTaskPage {

    private final SelenideElement taskNameInptut =
            $x("//label[contains(text(), 'Название задания')]/following-sibling::div//input");

    private final SelenideElement taskDescriptionTextarea = $("textarea[placeholder='Введите текст описания']");
    private final SelenideElement saveButton = $(byText("Сохранить"));
    private final SelenideElement openListOfSpaces = $x("//*[@id='simple-tabpanel-0']/div/div[1]/div/div/div[1]/div/div[1]");
    private final SelenideElement spaceActiveSelection = $(byText("Торнберри 23"));

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
        taskDescriptionTextarea.click();
        taskDescriptionTextarea.setValue(value);

        return this;
    }

    public CreateSupplyTaskPage clickSaveButton() {
        saveButton.click();

        return this;
    }

    public CreateSupplyTaskPage clickButtonOpenActiveSpaces() {
        openListOfSpaces.click();

        return this;
    }

    public CreateSupplyTaskPage spaceSelection() {
        spaceActiveSelection.click();

        return this;
    }

   public CreateSupplyTaskPage checkStateTables(){
       taskNameInptut.$$("[id=':ri:-label']")
               .forEach(label ->
                       label.shouldHave(cssValue("border-color", "rgb(220, 56, 49)"))
               );

       return this;
   }
    public CreateSupplyTaskPage checkValidationMessage(String value){

        taskNameInptut.$("[id=':ri:-label']")
                .$(byText(value));

        return this;
    }


}
