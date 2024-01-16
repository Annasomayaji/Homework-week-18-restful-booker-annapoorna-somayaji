package com.restful.booker.testsuite;

import com.restful.booker.testbase.TestBase;
import com.restful.booker.utils.PropertyReader;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PartialUpdateBookingAssertion extends TestBase {
    static ValidatableResponse response;
    String getBooking = PropertyReader.getInstance().getProperty("getBooking");


    @Test
    public void updateBooking() {

        //Creating a has map for the POST body. and passing it
        //To do: try to move data to PoJo class and use getter and setter method here
        HashMap data; data = new HashMap();
        data.put("firstname", "James");
        data.put("lastname", "Smith");



        response = given()
                .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .auth().basic("admin", "password123")
                .pathParam("id", 1412) //hard coding id value
                .header("Content-Type", "application/json")
                .header("Cookie", "token=abc123")
                .when()
                .body(data) //passing the hash map of data for body
                .patch(getBooking + "/{id}")
                .then();
        response.log().all();

        //Assertion 1: Validate status code
        response.statusCode(200);

        //Assertion 2: Validate response header
        response.header("Content-Type", "application/json; charset=utf-8");

        //Assertion 3: Validate response time
        response.time(Matchers.lessThan(2000L));

        //Assertion 4: Validate Response body
        response.body("firstname", equalTo(data.get("firstname")));
        response.body("lastname", equalTo(data.get("lastname")));

        System.out.println("Assertion 1: Status code validated");
        System.out.println("Assertion 2: Response header validated");
        System.out.println("Assertion 3: Response time validated");
        System.out.println("Assertion 4: Data in response body validated");

    }


}
