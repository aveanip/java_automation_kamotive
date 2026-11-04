package tests;

import com.github.javafaker.Faker;
import models.Users.UserCreateRequestModel;

import java.util.Locale;

public class TestDataGenerator {
    private static final Faker faker = new Faker(new Locale("ru"));
    private static final Faker enFaker = new Faker(new Locale("en"));
    public static UserCreateRequestModel generateValidUser() {
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();

        String randomLogin = enFaker.name().username() + enFaker.number().numberBetween(1000, 9999);
        String validLatinEmail = enFaker.name().username() + "@test.kamaz.ru";

        return UserCreateRequestModel.builder()
                .firstName(firstName)
                .lastName(lastName)
                .middleName("")
                .login(randomLogin)
                .email(validLatinEmail)
                .phoneNumber("+7 9" + faker.number().digits(9))
                .organizationId("049003a4-5d9e-416c-9798-36847deeb610")
                .build();
    }

    public static UserCreateRequestModel generateUserInvalidLogin() {
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();

        String invalidLogin = faker.name().username() + faker.number().numberBetween(1000, 9999);
        String validLatinEmail = enFaker.name().username() + "@test.kamaz.ru";

        return UserCreateRequestModel.builder()
                .firstName(firstName)
                .lastName(lastName)
                .middleName("")
                .login(invalidLogin)
                .email(validLatinEmail)
                .phoneNumber("+7 9" + faker.number().digits(9))
                .organizationId("049003a4-5d9e-416c-9798-36847deeb610")
                .build();
    }
}

