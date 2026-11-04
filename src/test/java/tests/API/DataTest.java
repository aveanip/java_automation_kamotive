package tests.API;

import Api.ApiClient;

public class DataTest {

    static String invalidUserID = "31e9a457-43e7-45c0-8f04-555a83acd1d4";
    static String error404Message = String.format("404 NOT_FOUND \"Пользователь с ИД: %s не найден\"", invalidUserID);
    static String expectedTokenPath = "eyJ";
    static String error422Message = "Логин может содержать только латинские буквы, цифры, точки, дефисы и подчеркивания, но не подряд. Длина от 3 до 150 символов.";
    static String errorField = "login";
    static String errorMessage = "Ошибка валидации";
    static String activeStatusId = "7c7efa0c-ccdc-4e0b-a009-e1d4c1b84657";
    static String orgId = "049003a4-5d9e-416c-9798-36847deeb610";
}
