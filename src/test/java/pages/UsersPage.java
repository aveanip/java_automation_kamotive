package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import java.time.Duration;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class UsersPage extends BasePage{


    public UsersPage openPage() {
        open(pageUrl);
        return this;
    }

    public UsersPage clickButtonCreateUser() {
        buttonCreate.click();
        return this;
    }

    @Step("Проверить текст заголовка на модальном окне")
    public UsersPage checkTheTitle() {
        $(byText("Регистрация нового пользователя")).shouldBe(visible);
        return this;
    }

    @Step("Заполнить поле 'Фамилия' значением '{lastName}'")
    public UsersPage setLastName(String lastName) {
        fieldLastName.setValue(lastName);
        return this;
    }

    @Step("Заполнить поле 'Имя' значением '{firstName}'")
    public UsersPage setFirstName(String firstName) {
        fieldFirstName.setValue(firstName);
        return this;
    }

    @Step("Заполнить поле 'Логин' значением '{login}'")
    public UsersPage setLogin(String login) {
        fieldLogin.setValue(login);
        return this;
    }

    @Step("Заполнить поле 'Почта' значением '{email}'")
    public UsersPage setEmail(String email) {
        fieldEmail.setValue(email);
        return this;
    }

    public UsersPage setPhoneNumber(String phoneNumber) {
        fieldPhone.setValue(phoneNumber);
        return this;
    }

    @Step("Выбрать организацию '{orgName}' из выпадающего списка")
    public UsersPage selectOrganization(String orgName) {
        organizationField.click();
        organizationField.setValue(orgName);
        $$("[role='option']")
                .first()
                .shouldBe(visible, Duration.ofSeconds(3))
                .click();
        return this;
    }

    @Step("Нажать кнопку 'Сохранить'")
    public UsersPage clickSave() {
        buttonSave.click();
        return this;
    }

    @Step("Проверить, что модальное окно закрылось")
    public void checkModalClosed() {
        $(byText("Регистрация нового пользователя")).shouldNotBe(visible);
    }

    @Step("Проверить, что пользователь '{login}' появился в списке")
    public UsersPage checkUserExistsInList(String login) {
        userRows
                .find(byText(login));
        return this;
    }

    @Step("Выбрать первого пользователя в списке")
    public UsersPage clickFirstUserInTheList(String firstName, String lastName) {
            String fullName = lastName + " " + firstName;
            $(byText(fullName))
                    .shouldBe(visible)
                    .click();
        return this;
    }

    @Step("Проверить Фамилию")
    public String getLastName() {
        return fieldLastName.shouldBe(visible).getValue();
    }

    @Step("Проверить Имя")
    public String getFirstName() {
        return fieldFirstName.shouldBe(visible).getValue();
    }

    @Step("Проверить Статус")
    public String getStatusUser() {
        return fieldStatus.shouldBe(visible).getValue();
    }

    @Step("Кликнуть на кнопку выбора статуса , и выбрать статус 'Активировать'")
    public UsersPage clickButtonStatus() {
        statusSelectionButton.click();
        fieldStatus.click();
        return this;
    }
}








