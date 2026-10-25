package Api;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.Spaces.*;

import static io.restassured.RestAssured.given;
import static specs.BaseSpecs.baseRequestSpec;
import static specs.spaces.SpacesSpecs.*;

public class ApiSpaces {

    @Step("Создание валидного пространства с заполнением всех полей")
    public SpacesResponseModel successfulCreationOfSpace(SpacesRequestBodyModel spacesData, String token) {
        return given(baseRequestSpec)
                .header("Authorization", "Bearer " + token)
                .header("Realm", "kamotive")
                .contentType(ContentType.JSON)
                .body(spacesData)
                .when()
                .patch("/core/api/v2/spaces")
                .then()
                .spec(successfulCreationOfSpaceSpecs)
                .extract()
                .as(SpacesResponseModel.class);
    }


    @Step("Попытка создание пространства с уже существующим именем")
    public ErrorWhenCreatingTheSpaceModel duplicateSpaceNameValidation(SpacesRequestBodyModel spacesData, String token) {
        return given(baseRequestSpec)
                .header("Authorization", "Bearer " + token)
                .header("Realm", "kamotive")
                .contentType(ContentType.JSON)
                .body(spacesData)
                .when()
                .patch("/core/api/v2/spaces")
                .then()
                .spec(duplicateSpaceNameValidationSpecs)
                .extract()
                .as(ErrorWhenCreatingTheSpaceModel.class);
    }

    @Step("Попытка добавление пользователя в созданное пространство")
    public Response successfulUserAdd(String spaceId, String userId, String token) {
        UserReferenceModel reference = UserReferenceModel.builder()
                .id(userId)
                .type("users")
                .build();

        AddUserToSpaceRequestModel request = AddUserToSpaceRequestModel.builder()
                .reference(reference)
                .build();

        return given(baseRequestSpec)
                .header("Authorization", "Bearer " + token)
                .header("Realm", "kamotive")
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .patch("/core/api/v2/spaces/{spaceId}/users", spaceId)
                .then()
                .spec(successfulUserAdditionSpecs)
                .extract()
                .response();
    }

    @Step("Получение списка пользователей пространства {spaceId}")
    public Response getUsersInSpace(String spaceId, String token) {
        return given(baseRequestSpec)
                .header("Authorization", "Bearer " + token)
                .header("Realm", "kamotive")
                .queryParam("page", 0)      // Номер страницы
                .queryParam("size", 20)     // Размер страницы
                .queryParam("tab", "users") // Вкладка
                .when()
                .get("/core/api/v2/spaces/{spaceId}/users", spaceId)
                .then()
                .statusCode(200)
                .extract()
                .response();
    }
}
