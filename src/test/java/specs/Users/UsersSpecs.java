package specs.Users;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;
import models.Users.Metadata;
import models.Users.UserCreateRequestWithInvalidLoginModel;

import static io.restassured.filter.log.LogDetail.ALL;
import static org.hamcrest.Matchers.*;

public class UsersSpecs {


    public static ResponseSpecification successfulCreationUserSpecs = new ResponseSpecBuilder()
            .log(ALL)
            .expectStatusCode(201)
            .expectBody("success", equalTo(true))
            .expectBody("errorCode", nullValue())
            .expectBody("errorMessage", nullValue())
            .expectBody("metadata.artifactId", notNullValue())
            .build();

    public static ResponseSpecification checkingTheListOfUsersByOrganizationSpecs = new ResponseSpecBuilder()
            .log(ALL)
            .expectStatusCode(200)
            .expectBody("_embedded.userDtoes", not(empty()))
            // 4. Проверяем, что у пользователей внутри есть обязательные поля (берем первого пользователя)
            .expectBody("_embedded.userDtoes[0].id", notNullValue())
            .expectBody("_embedded.userDtoes[0].login", notNullValue())
            .expectBody("_embedded.userDtoes[0].organization.id", notNullValue())
            // 5. Проверяем корректность пагинации
            .expectBody("page.size", equalTo(20))          // Размер страницы (20)
            .expectBody("page.totalElements", greaterThan(0)) // Всего пользователей > 0
            .expectBody("page.totalPages", greaterThan(0))    // Всего страниц > 0
            .expectBody("page.number", equalTo(0))          // нумерация с 0
            .build();

    public static ResponseSpecification fillingInTheLoginFieldWithInvalidDataSpecs = new ResponseSpecBuilder()
            .log(ALL)
            .expectStatusCode(422)
            .expectBody("status", notNullValue())
            .expectBody("message", notNullValue())
            .build();

    public static ResponseSpecification activationUserSpecs = new ResponseSpecBuilder()
            .log(ALL)
            .expectStatusCode(200)
            .build();
    public static ResponseSpecification successfullyDeleteUserSpecs = new ResponseSpecBuilder()
            .log(ALL)
            .expectStatusCode(200)
            .build();

    public static ResponseSpecification errorDeleteUserSpecs = new ResponseSpecBuilder()
            .log(ALL)
            .expectStatusCode(404)
            .expectBody("status", notNullValue())

            .build();
    public static ResponseSpecification avatarUserSpecs = new ResponseSpecBuilder()
            .log(ALL)
            .expectStatusCode(200)
            .build();

    public static ResponseSpecification deleteUserSpecs = new ResponseSpecBuilder()
            .log(ALL)
            .expectStatusCode(200)
            .build();
    }
