package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.CreateSupplyTaskPage;
import pages.SupplyPage;


import static tests.testsdata.TestData.*;

public class SupplyTest extends BaseTest {
//    CreateSupplyTaskPage createSupplyTaskPage = new CreateSupplyTaskPage();

    @Test
    @DisplayName("Создание задания на поставку и проверка его отображения в списке")
    void addNewSupply() {
        SupplyPage supplyPage = new SupplyPage();
        supplyPage
                .openPage()
                .clickCreateNewTaskButton()
                .taskName(supplyTaskName)
                .clickSaveButton();
        supplyPage.openPage()
                .checkCreatedTask(supplyTaskName);

    }


}
