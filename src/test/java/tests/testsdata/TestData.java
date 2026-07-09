package tests.testsdata;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestData {

    public static String baseUrl = "https://test.kamotive.ru";


    //авторизация
    public static String title = "Вход в портал Kamotive";

    public static String login = "eliza";
    public static String password = "ztsTUfMwa6GWsi2)V1";

    public static String invalidLogin = "eliz";
    public static String invalidPassword = "ztsTUfMwa6GWsi2";
    public static String errorText = "Неверный логин или пароль";


    //Главная страница
    public static String fullUserName = "Торнберри Элайза";

    //Поставка
    public static String supplyTaskName = "Поставка_" + LocalDateTime.now()
            .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss_SSS"));
}

