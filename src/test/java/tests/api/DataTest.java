package tests.api;

public class DataTest {

    public static String INVALID_USER_ID = "31e9a457-43e7-45c0-8f04-555a83acd1d4";
    public static String ERROR_404_MESSAGE = String.format("404 NOT_FOUND \"Пользователь с ИД: %s не найден\"", INVALID_USER_ID);
    public static String EXPECTED_TOKEN_PATH = "eyJ";
    public static String ERROR_422_MESSAGE = "Логин может содержать только латинские буквы, цифры, точки, дефисы и подчеркивания, но не подряд. Длина от 3 до 150 символов.";
    public static String ERROR_FIELD = "login";
    public static String ERROR_MESSAGE = "Ошибка валидации";
    public static String ACTIVE_STATUS_ID = "7c7efa0c-ccdc-4e0b-a009-e1d4c1b84657";
    public static String ORG_ID = "049003a4-5d9e-416c-9798-36847deeb610";
}
