package tests.UI;

import models.Users.UserCreateRequestModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.UsersPage;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;
import static tests.TestDataGenerator.generateValidUser;
import static tests.UI.TestData.login;
import static tests.UI.TestData.password;

public class UsersUITests extends BaseUITest {
    UsersPage user = new UsersPage();
    UserCreateRequestModel newUser = generateValidUser();

    @Test
    @DisplayName("Создание пользователя через UI")
    public void successfulAuthorizationWithValidDataTest() {
        step("Открыть страницу авторизации", () -> {
            authorizationPage
                    .openPage();
        });
        step("Ввести валидные данные и выполнить вход", () -> {
            authorizationPage
                    .login(login, password)
                    .checkHomePageIsOpened();
        });

        step("Открываем страницу и создаём нового пользователя", () -> {
            user
                    .openPage()
                    .clickButtonCreateUser()
                    .checkTheTitle()
                    .setLastName(newUser.getLastName())
                    .setFirstName(newUser.getFirstName())
                    .setLogin(newUser.getLogin())
                    .setEmail(newUser.getEmail())
                    .setPhoneNumber(newUser.getPhoneNumber())
                    .selectOrganization("ПАО \"КАМАЗ\"")
                    .clickSave()
                    .checkModalClosed();
            step("Проверяем, что пользователь появился в списке", () ->
                    user.checkUserExistsInList(newUser.getLogin()));
        });
    }

    @Test
    @DisplayName("Активировать пользователя через UI")
    public void successfulUserActivationTest() {

        String lastName = newUser.getLastName();
        String firstName = newUser.getFirstName();
        String loginTest = newUser.getLogin();
        String email = newUser.getEmail();
        String phone = newUser.getPhoneNumber();
        String orgName = "ПАО \"КАМАЗ\"";

        step("Открыть страницу авторизации", () -> {
            authorizationPage
                    .openPage();
        });
        step("Ввести валидные данные и выполнить вход", () -> {
            authorizationPage
                    .login(login, password)
                    .checkHomePageIsOpened();
        });
        step("Открываем страницу и создаём нового пользователя", () -> {
            user
                    .openPage()
                    .clickButtonCreateUser()
                    .checkTheTitle()
                    .setLastName(lastName)
                    .setFirstName(firstName)
                    .setLogin(loginTest)
                    .setEmail(email)
                    .setPhoneNumber(phone)
                    .selectOrganization(orgName)
                    .clickSave()
                    .checkModalClosed();
            step("Проверяем, что пользователь появился в списке", () ->
                    user.checkUserExistsInList(newUser.getLogin()));
        });
        step("Переходим в карточку пользователя ", () ->
                user.clickFirstUserInTheList(firstName, lastName));
        step("Проверить данные в карточке пользователя", () -> {
            assertThat(user.getLastName())
                    .as("Фамилия в карточке должна совпадать с сгенерированной")
                    .isEqualTo(lastName);
            assertThat(user.getFirstName())
                    .as("Имя в карточке должна совпадать с сгенерированной")
                    .isEqualTo(firstName);
        });
        step("Выбираем статус пользователя Активный", () ->
                user.clickButtonStatus());
    }
}
