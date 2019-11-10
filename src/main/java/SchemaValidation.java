import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

public class SchemaValidation {

    @Test
    @DisplayName("4.1 Check if types of fields are correct and all required fields exist")
    public void testSchema() {
        for (int i = 0; i <= Setup.COUNTRY_CODES.length - 1; i++) {
            String requestBody = Setup.getRequestPayload(Setup.COUNTRY_CODES[i]);
            Response response = RestAssured.given()
                    .contentType(ContentType.JSON)
                    .body(requestBody)
                    .when()
                    .post(Setup.BASE_URL);
            response.then().assertThat().body(matchesJsonSchemaInClasspath("response-schema-expected.json"));
        }
    }

    @Test
    @DisplayName("4.2 Check the Country field of the Response")
    public void testSchemaCountry() {
        RestAssured.defaultParser = Parser.JSON;

        for (int i = 0; i <= Setup.COUNTRY_CODES.length - 1; i++) {
            String requestBody = Setup.getRequestPayload(Setup.COUNTRY_CODES[i]);
            RestAssured.given()
                    .contentType(ContentType.JSON)
                    .body(requestBody)
                    .when()
                    .post(Setup.BASE_URL)
                    .then().body("user.address.country", equalTo(Setup.COUNTRY_CODES[i]));
        }
    }

    @Test
    @DisplayName("4.3 Check 500-schema is correct")
    public void test500_Schema() {
            String requestBody = Setup.getRequestPayload(Setup.COUNTRY_CODES[1]);
            Response response = RestAssured.given()
                    .body(requestBody)
                    .when()
                    .post(Setup.BASE_URL);
            response.then().assertThat().body(matchesJsonSchemaInClasspath("response500-schema-expected.json"));
    }
}