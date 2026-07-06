package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SupplyPage {

    private final SelenideElement createTaskButton =$("[data-test-id='supp-list-create-task-button']");

    public SupplyPage openPage() {
        open("/supply/list?page=0&size=20");

        return this;
    }

    public SupplyPage ClickСreateNewTaskButton(){
        createTaskButton.click();

        return this;
    }
}
