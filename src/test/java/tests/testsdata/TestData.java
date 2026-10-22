package tests.testsdata;

import com.github.javafaker.Faker;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.stream.Stream;

public class TestData {

    public static String baseUrl = "https://dev.kamotive.ru";

    Faker faker = new Faker();
    Faker fakerRU = new Faker(new Locale("ru"));
    //авторизация
    public static String title = "Вход в портал Kamotive";

    public static String login = "ntstin125";
    public static String password = "ztsT1UfMwa6GWsi2)VztsTUfMwa6GWsi2)V1";

    public static String invalidLogin = "eliz";
    public static String invalidPassword = "ztsTUfMwa6GWsi2";
    public static String errorText = "Неверный логин или пароль";


    //Главная страница
    public static String fullUserName = "Торнберри Элайза";

    //Поставка

    public String supplyTaskLongName = fakerRU.lorem().characters( 150);
    public String supplyTaskShortName = fakerRU.lorem().characters(1);
    public String supplyTaskEnName = faker.lorem().characters(15);

    public String supllyDescriptionLong = faker.lorem().characters(300);
    public String supplyDescriptionShortName = faker.lorem().characters(1);
    public String descriptionIsEmpty = faker.lorem().characters(0);

    public String taskNameExceedingLimit = faker.lorem().characters(151);
    public String nameIsEmpty = faker.lorem().characters(0);

    public String taskDescriptionExceedingLimit = faker.lorem().characters(301);



//
//            "Поставка_" + LocalDateTime.now()
//                    .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss_SSS"));
}

