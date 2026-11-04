package tests.UI;

import com.github.javafaker.Faker;

import java.util.Locale;

public class TestData {



    Faker faker = new Faker();
    Faker fakerRU = new Faker(new Locale("ru"));
    //авторизация
    public static String title = "Вход в портал Kamotive";

    public static String login = "Lk.kamotive";
    public static String password = "v6x7gM2m5Yu8!";

    public static String invalidLogin = "eliz";
    public static String invalidPassword = "ztsTUfMwa6GWsi2";
    public static String errorText = "Неверный логин или пароль";

}

