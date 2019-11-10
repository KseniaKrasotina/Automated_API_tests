import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.*;
//import org.testng.annotations.Test;


public class ApiStatusCodes {

    private static final String[] INCORRECT_COUNTRY_CODES = {"1", ""};

    @Test
    @DisplayName("1.1. Checking status. Correct HTTP type. POST request. Correct Country-codes")
    public void testPostCorrectCountryCodes()
    {
        for (int i = 0; i <= Setup.COUNTRY_CODES.length-1; i++) {
            String requestBody = Setup.getRequestPayload(Setup.COUNTRY_CODES[i]);

            RestAssured.given()
                    .contentType(ContentType.JSON)
                    .body(requestBody)
                    .when()
                    .post(Setup.BASE_URL)
                    .then()
                    .statusCode(201);
        }
    }

    @Test
    @DisplayName("1.2. Checking status code. Correct HTTP type. POST request. Incorrect country-codes")
    public void testPostIncorrectCountryCodes()
    {
        for (int i = 0; i <= INCORRECT_COUNTRY_CODES.length-1; i++) {
            String requestBody = Setup.getRequestPayload(INCORRECT_COUNTRY_CODES[i]);
            RestAssured.given()
                    .contentType(ContentType.JSON)
                    .body(requestBody)
                    .when()
                    .post(Setup.BASE_URL)
                    .then()
                    .statusCode(200);
        }
    }

    @Test
    @DisplayName("1.3. Checking status code. Correct HTTP type. POST request. Empty body")
    public void testEmptyBody()
    {
            RestAssured.given()
                    .contentType(ContentType.JSON)
                    .when()
                    .post(Setup.BASE_URL)
                    .then()
                    .statusCode(400);
    }

    @Test
    @DisplayName("1.4 Checking status code. Incorrect HTTP type. GET request")
    public void testGet()
    {
        RestAssured.given()
                .when()
                .get(Setup.BASE_URL)
                .then()
                .statusCode(404);
    }

    @Test
    @DisplayName("1.5. Checking status. Correct HTTP type. POST request. Incorrect content-type")
    public void testIncorrectContentType()
    {
        String requestBody = Setup.getRequestPayload(Setup.COUNTRY_CODES[0]);
        RestAssured.given()
                    .contentType(ContentType.XML)
                    .body(requestBody)
                    .when()
                    .post(Setup.BASE_URL)
                    .then()
                    .statusCode(415);
    }

    @Test
    @DisplayName("1.6. Checking status. Correct HTTP type. POST request. Empty content-type")
    public void testEmptyContentType()
    {
        String requestBody = Setup.getRequestPayload(Setup.COUNTRY_CODES[0]);
        RestAssured.given()
                .body(requestBody)
                .when()
                .post(Setup.BASE_URL)
                .then()
                .statusCode(415);
    }

    @Test
    @DisplayName("3.3. Verify response for heavy request payload")
    public void testHeavyRequest()
    {
        String heavyPayload = "";
        for (int i = 0; i < 1024*1024; i++)
        {
            heavyPayload += "A";
        }

        RestAssured.given()
                .body(heavyPayload)
                .when()
                .post(Setup.BASE_URL)
                .then()
                .statusCode(415);
    }
}