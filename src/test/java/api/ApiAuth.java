package api;

import io.qameta.allure.Step;
import io.restassured.filter.session.SessionFilter;
import io.restassured.http.ContentType;
import models.auth.AuthRequestBodyModel;
import models.auth.AuthResponseModel;
import models.auth.ErrorResponseModel;

import static io.restassured.RestAssured.given;
import static specs.BaseSpecs.baseRequestSpec;
import static specs.auth.AuthSpecs.invalidInputData;
import static specs.auth.AuthSpecs.successfulLoginRequestSpec;

public class ApiAuth {
    private final SessionFilter sessionFilter = new SessionFilter();

    @Step("Предварительный запрос для получения куки сессии")
    public void preLoadLoginPage() {
        given(baseRequestSpec)
                .filter(sessionFilter) // Включаем фильтр
                .when()
                .get("/sign-in/kamotive") // Заходим на страницу логина, чтобы сервер дал куки
                .then()
                .statusCode(200);
    }

    @Step("Авторизация и получение токена")
    public AuthResponseModel login(AuthRequestBodyModel loginData) {
        preLoadLoginPage();
        return given(baseRequestSpec)
                .header("Accept", "application/hal+json")
                .contentType(ContentType.JSON)
                .body(loginData)
                .when()
                .post("/core/api/v1/login")
                .then()
                .spec(successfulLoginRequestSpec)
                .extract()
                .as(AuthResponseModel.class);
    }

    @Step("Ошибка при не верно введенных данных")
    public ErrorResponseModel invalidLogin(AuthRequestBodyModel loginData) {
        preLoadLoginPage();
        return given(baseRequestSpec)
                .header("Accept", "application/hal+json")
                .contentType(ContentType.JSON)
                .body(loginData)
                .when()
                .post("/core/api/v1/login")
                .then()
                .spec(invalidInputData)
                .extract()
                .as(ErrorResponseModel.class);

    }
}
