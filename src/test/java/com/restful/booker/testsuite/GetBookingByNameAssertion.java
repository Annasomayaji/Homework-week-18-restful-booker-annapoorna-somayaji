package com.restful.booker.testsuite;

import com.restful.booker.testbase.TestBase;
import com.restful.booker.utils.PropertyReader;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasKey;

public class GetBookingByNameAssertion extends TestBase {

    String getBooking= PropertyReader.getInstance().getProperty("getBooking");

    @Test
    public void getBooking() {

        //Store query parameters in hash map and pass in given()
        Map<String, String> qParams = new HashMap<>();
        qParams.put("firstname", "Sally");
        qParams.put("lastname", "Brown");

        ValidatableResponse response = given()
                .queryParams(qParams)
                .when()
                .get(getBooking)
                .then()
                //Assertion 1: validate response header
                .header("Content-Type","application/json; charset=utf-8")
                //Assertion 2:validate key in the response by going to root and first entry
                .body("[0]",hasKey("bookingid"))
                //Assertion 3:validate status code of response
                .statusCode(200);
        response.log().all();

        List<HashMap<String, Object>> records = response.extract().path("$");
        //Assertion 4: Validate response is not empty
        Assert.assertFalse(records.isEmpty());

        System.out.println("Assertion 1: Response header validated.");
        System.out.println("Assertion 2: Key 'bookingid' presence in response body validated");
        System.out.println("Assertion 3: Status code of response is validated");
        System.out.println("Assertion 4: Response body 'not empty' validated");


    }

}
