package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class CreateSupplyTaskPage {

    private SelenideElement taskNameInptut =
            $x("//label[contains(text(), 'Название задания')]/following-sibling::div//input");


    public CreateSupplyTaskPage taskName(String value){
        taskNameInptut.setValue(value);

        return this;
    }
}
