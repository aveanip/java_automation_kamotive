package api;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import models.users.ActionResponse;
import models.users.UserCreateRequestModel;
import models.users.UserCreateRequestWithInvalidLoginModel;
import models.users.UserCreateResponseModel;

import java.io.File;

import static io.restassured.RestAssured.given;
import static specs.BaseSpecs.baseRequestSpec;
import static specs.users.UsersSpecs.*;

public class ApiUser {

    @Step("Создание пользователя заполнением всех полей")
    public UserCreateResponseModel successfulCreationUser(UserCreateRequestModel userData, String token) {
        return given(baseRequestSpec)
                .header("Authorization", "Bearer " + token)
                .header("Realm", "kamotive")
                .contentType(ContentType.JSON)
                .body(userData)
                .when()
                .post("/core/api/v1/users")
                .then()
                .spec(successfulCreationUserSpecs)
                .extract()
                .as(UserCreateResponseModel.class);
    }


    @Step("Получение списка пользователей организации {organizationId}")
    public static ValidatableResponse getUsersByOrganization(String organizationId, String token) {
        return given()
                .header("Authorization", "Bearer " + token)
                .header("Realm", "kamotive")
                .queryParam("organization.id", organizationId)  // Фильтр по организации
                .queryParam("sort", "createDate,DESC") // Сортировка по дате создания (новые первые)
                .when()
                .get("core/api/v2/users")
                .then()
                .spec(checkingTheListOfUsersByOrganizationSpecs);
    }

    @Step("Создание пользователя c заполнение поля логин невалидными данными")
    public UserCreateRequestWithInvalidLoginModel fillingInTheLoginFieldWithInvalidData(UserCreateRequestModel userData, String token) {
        return given(baseRequestSpec)
                .header("Authorization", "Bearer " + token)
                .header("Realm", "kamotive")
                .contentType(ContentType.JSON)
                .body(userData)
                .when()
                .post("/core/api/v1/users")
                .then()
                .spec(fillingInTheLoginFieldWithInvalidDataSpecs)
                .extract()
                .as(UserCreateRequestWithInvalidLoginModel.class);

    }

    @Step("Активация созданного пользователя")
    public ActionResponse userActivation(String userId, String statusId, String token) {
        return given(baseRequestSpec)
                .header("Authorization", "Bearer " + token)
                .header("Realm", "kamotive")
                .contentType(ContentType.JSON)
                .pathParam("userId", userId)         // Подставляем в URL вместо {userId}
                .pathParam("statusId", statusId)
                .when()
                .put("/core/api/v1/users/{userId}/status/{statusId}")
                .then()
                .spec(response200Specs)
                .extract()
                .as(ActionResponse.class);
    }

    @Step("Загрузка аватара для пользователя {userId}")
    public static ValidatableResponse uploadAvatar(String userId, File avatarFile, String token) {
        return given()
                .header("Authorization", "Bearer " + token)
                .header("Realm", "kamotive")
                .multiPart("file", avatarFile)
                .pathParam("userId", userId)
                .when()
                .post("/core/api/v1/users/{userId}/avatar")
                .then()
                .spec(response200Specs);
    }

    @Step("Удаление аватара для пользователя {userId}")
    public static ValidatableResponse deleteAvatar(String userId, String token) {
        return given()
                .header("Authorization", "Bearer " + token)
                .header("Realm", "kamotive")
                .pathParam("userId", userId)
                .when()
                .delete("/core/api/v1/users/{userId}/avatar")
                .then()
                .spec(response200Specs);
    }

    @Step("Успешное удаление пользователя")
    public ActionResponse deleteUser(String userId, String token) {
        return given(baseRequestSpec)
                .header("Authorization", "Bearer " + token)
                .header("Realm", "kamotive")
                .contentType(ContentType.JSON)
                .pathParam("userId", userId)
                .when()
                .delete("/core/api/v1/users/{userId}")
                .then()
                .spec(response200Specs)
                .extract()
                .as(ActionResponse.class);
    }

    @Step("Удаление несуществующего пользователя")
    public UserCreateRequestWithInvalidLoginModel errorDeleteUser(String userId, String token) {
        return given(baseRequestSpec)
                .header("Authorization", "Bearer " + token)
                .header("Realm", "kamotive")
                .contentType(ContentType.JSON)
                .pathParam("userId", userId)
                .when()
                .delete("/core/api/v1/users/{userId}")
                .then()
                .spec(errorDeleteUserSpecs)
                .extract()
                .as(UserCreateRequestWithInvalidLoginModel.class);
    }
}
