package tests;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import tests.testsdata.TestData;
import tests.utils.RandomUtils;


import java.util.stream.Stream;

public class SupplyTest extends BaseUITest {


    @MethodSource("supplyTaskNamesProvider")
    @ParameterizedTest(name = "[{index}] Создание задания на поставку с именем: {0}")
    void addNewSupply(String testCaseName, String name) {
        supplyPage
                .openPage()
                .clickCreateNewTaskButton()
                .clickButtonOpenActiveSpaces()
                .spaceSelection()
                .taskName(name)
                .clickSaveButton();
        supplyPage.openPage()
                .checkCreatedTaskContains(name);

    }

    static Stream<Arguments> supplyTaskNamesProvider() {
        TestData testData = new TestData();
        return Stream.of(
                Arguments.of
                        ("150 символов", testData.supplyTaskLongName),
                                Arguments.of
                                        ("1 символ", testData.supplyTaskShortName),
                                                Arguments.of
                                                        ("Название на английском языке", testData.supplyTaskEnName
                                                        ));
    }

    @MethodSource("negativeNameSupplyTaskNamesProvider")
    @ParameterizedTest(name = "[{index}] Валидация названия поставки: {0}")
    void negativeNameChecksSupply(String testCaseName, String name, String validationMessage) {
        supplyPage
                .openPage()
                .clickCreateNewTaskButton()
                .clickButtonOpenActiveSpaces()
                .spaceSelection()
                .taskName(name)
                .checkStateTables()
                .checkValidationMessage(validationMessage);

    }

    static Stream<Arguments> negativeNameSupplyTaskNamesProvider() {
        TestData testData = new TestData();
        return Stream.of(
                Arguments.of
                        ("Превышен лимит 150 символов", testData.taskNameExceedingLimit, "Название может содержать максимум 150 символов"),
                Arguments.of
                        ("Название пустое", testData.nameIsEmpty, "Название не может быть пустым"),
                Arguments.of
                        ("Запрещенные символы", RandomUtils.getRandomForbiddenCharacters(),
                                "Название может содержать только печатные символы, включая буквы, цифры и знаки препинания"),
                Arguments.of
                        ("Только пробелы", "   ", "Название не может быть пустым")
        );
    }


    @MethodSource("supplyTaskNamesProvider")
    @ParameterizedTest(name = "[{index}] Создание задания на поставку с именем: {0}")
    void addNewSupply(String testCaseName, String name, String description) {
        supplyPage
                .openPage()
                .clickCreateNewTaskButton()
                .clickButtonOpenActiveSpaces()
                .spaceSelection()
                .taskName(name)
                .taskDescription(description)
                .clickSaveButton();
        supplyPage.openPage()
                .checkCreatedTask(name)
                .checkCreatedDescription(description);

    }

    static Stream<Arguments> supplyTaskDescriptionProvider() {
        TestData testData = new TestData();
        return Stream.of(
                Arguments.of
                        ("300 символов", testData.supplyTaskEnName, testData.supllyDescriptionLong ),
                Arguments.of
                        ("1 символ", testData.supplyTaskEnName, testData.supplyDescriptionShortName )
//                Arguments.of
//                        ("Описание на английском языке", testData.supplyTaskEnName, testData.
                        );
    }
}
