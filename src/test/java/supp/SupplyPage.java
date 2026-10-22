package supp;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import tests.utils.RandomUtils;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;


public class SupplyPage {

    private final SelenideElement createTaskButton = $("[data-test-id='supp-list-create-task-button']");

    public SupplyPage openPage() {
        open("/supply/list?page=0&size=20");
        createTaskButton.shouldBe(visible, Duration.ofSeconds(10));

        return this;
    }

    public CreateSupplyTaskPage clickCreateNewTaskButton() {
        createTaskButton.click();

        return new CreateSupplyTaskPage();
    }

    public SupplyPage checkCreatedTask(String value) {
        $(byText(value)).shouldBe(visible);

        return this;
    }

    public SupplyPage checkCreatedTaskContains(String name) {
        int checkLength = Math.min(38, name.length());
        String expectedName = name.substring(0, checkLength);
        $(".MuiTableBody-root.host-1xnox0e")
                .$(byText(expectedName)).shouldBe(Condition.visible);

        return this;
    }

    public SupplyPage checkCreatedDescription(String value) {
        $(byText(value)).shouldBe(visible);

        return this;
    }


}
