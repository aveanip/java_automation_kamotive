package specs.spaces;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.filter.log.LogDetail.ALL;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static java.util.Objects.isNull;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;

public class SpacesSpecs {
    public static ResponseSpecification successfulCreationOfSpaceSpecs = new ResponseSpecBuilder()
            .log(ALL)
            .expectStatusCode(200)
            .expectBody("id", notNullValue())
            .expectBody("name", notNullValue())
            .expectBody("description", notNullValue())
            .expectBody("iconName", notNullValue())
            .expectBody("organization", notNullValue())
            .expectBody("createDate", notNullValue())
            .expectBody("updateDate", nullValue())
            .build();

    public static ResponseSpecification duplicateSpaceNameValidationSpecs = new ResponseSpecBuilder()
            .log(ALL)
            .expectStatusCode(409)
            .expectBody("status", notNullValue())
            .expectBody("message", notNullValue())
            .build();

    public static ResponseSpecification successfulUserAdditionSpecs = new ResponseSpecBuilder()
            .log(ALL)
            .expectStatusCode(200)
            .expectBody("access.id", notNullValue())
            .expectBody("access.name", notNullValue())
            .expectBody("reference.id", notNullValue())
            .expectBody("reference.name", notNullValue())
            .build();
}
