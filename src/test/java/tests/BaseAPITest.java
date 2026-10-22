package tests;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class BaseAPITest {

    @BeforeAll
    public static void setUpUi() {

        RestAssured.baseURI = "https://dev.kamotive.ru";
//        RestAssured.basePath = "/api/v1";
    }
}
