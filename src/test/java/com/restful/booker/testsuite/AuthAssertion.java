package com.restful.booker.testsuite;

import com.restful.booker.model.AuthPojo;
import com.restful.booker.testbase.TestBase;
import com.restful.booker.utils.PropertyReader;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class AuthAssertion extends TestBase {
    static String username = "admin";
    static String password = "password123";
    static ValidatableResponse response;
    static String token;

    String createAuth = PropertyReader.getInstance().getProperty("createAuth");

    @BeforeClass
    public void createAuthToken() {

        AuthPojo authPojo = new AuthPojo();
        authPojo.setUsername(username);
        authPojo.setPassword(password);
        response = given()
                .header("Content-Type", "application/json") //set request header
                .when()
                .body(authPojo)
                .post(createAuth)
                .then()
                //Assertion 1: validate response header
                .header("Content-Type", "application/json; charset=utf-8") //Verify response header
                //Assertion 2:validate status code of response
                .statusCode(200);
        System.out.println("Assertion 1: response header validated");
        System.out.println("Assertion 2: Status code validated");

    }

    //Assertion 3: verify token key is in the response body
    @Test
    public void verifyToken() {
        response.body("$", hasKey("token"));
        System.out.println("Assertion 3: Response body contains text 'token'");
    }


}
