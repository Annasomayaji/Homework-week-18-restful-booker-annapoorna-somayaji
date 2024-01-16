package com.restful.booker.testsuite;

import com.restful.booker.testbase.TestBase;
import com.restful.booker.utils.PropertyReader;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class HealthCheckAssertion extends TestBase {
    static ValidatableResponse response;
    String getHealthCheck = PropertyReader.getInstance().getProperty("getHealthCheck");

    @Test
    public void healthCheck() {
        response = given()
                .when()
                .get(getHealthCheck)
                .then();

        //Assertion 1: Validate status code
        response.statusCode(201);

        //Assertion 2: Validate header in response
        response.header("Content-Type", "text/plain; charset=utf-8");
        System.out.println("Assertion 1: Status code validated");
        System.out.println("Assertion 2: Response header validated");
    }


}
