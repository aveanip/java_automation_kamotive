package tests.API;

import Api.ApiClient;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class BaseAPITest {

    protected ApiClient api = new ApiClient();
    @BeforeAll
    public static void setUpGlobal() {
        RestAssured.baseURI = "https://kamotive.ru";
    }
}