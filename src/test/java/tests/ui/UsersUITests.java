package tests.ui;

import models.Users.UserCreateRequestModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.UsersPage;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;
import static tests.TestDataGenerator.generateValidUser;
import static tests.ui.TestData.*;

public class UsersUITests extends BaseUITest {
    UsersPage user = new UsersPage();
    UserCreateRequestModel newUser = generateValidUser();

    @Test
    @DisplayName("Создание пользователя через UI")
    public void successfulAuthorizationWithValidDataTest() {
        authorizationPage
                .openPage()
                .login(LOGIN, VALID_PASSWORD)
                .checkHomePageIsOpened();
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
        user.checkUserExistsInList(newUser.getLogin());
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
        authorizationPage
                .openPage()
                .login(LOGIN, VALID_PASSWORD)
                .checkHomePageIsOpened();
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
        user.checkUserExistsInList(newUser.getLogin())
                .clickFirstUserInTheList(firstName, lastName);
        assertThat(user.getLastName())
                .as("Фамилия в карточке должна совпадать с сгенерированной")
                .isEqualTo(lastName);
        assertThat(user.getFirstName())
                .as("Имя в карточке должна совпадать с сгенерированной")
                .isEqualTo(firstName);
        user.clickButtonStatus();
    }
}
