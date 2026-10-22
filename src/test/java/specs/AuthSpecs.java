package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.filter.log.LogDetail.ALL;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.notNullValue;

public class AuthSpecs {

    public static ResponseSpecification successfulLoginRequestSpec = new ResponseSpecBuilder()
            .log(ALL)
            .expectStatusCode(200)
            .expectBody(matchesJsonSchemaInClasspath("schema_auth/Successful_authorization_schemas.json"))
            .expectBody("access_token", notNullValue())
            .expectBody("refresh_token", notNullValue())
            .expectBody("token_type", notNullValue())
            .build();

    public static ResponseSpecification invalidInputData = new ResponseSpecBuilder()
            .log(ALL)
            .expectStatusCode(404)
            .expectBody(matchesJsonSchemaInClasspath("schema_auth/Invalid_user_data_schemas.json"))
            .expectBody("status", notNullValue())
            .expectBody("message", notNullValue())
            .build();


}
