package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import supp.SupplyPage;


import static tests.testsdata.TestData.*;

public class SupplyTest extends BaseTest {
//    CreateSupplyTaskPage createSupplyTaskPage = new CreateSupplyTaskPage();

    @Test
    @DisplayName("Создание задания на поставку и проверка его отображения в списке")
    void addNewSupply() {
        supplyPage
                .openPage()
                .clickCreateNewTaskButton()
                .taskName(supplyTaskName)
                .clickSaveButton();
        supplyPage.openPage()
                .checkCreatedTask(supplyTaskName);

    }


}
