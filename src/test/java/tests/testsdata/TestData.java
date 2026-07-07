package tests.testsdata;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestData {
//авторизация
public static String login = "eliza";
public static String password = "ztsTUfMwa6GWsi2)V1";

//Главная страница
    public static String fullUserName = "Торнберри Элайза";

//Поставка
public static String supplyTaskName = "Поставка_" + LocalDateTime.now()
    .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss_SSS"));
}

