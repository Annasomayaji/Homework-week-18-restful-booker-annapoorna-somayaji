package com.restful.booker.testsuite;

import com.restful.booker.testbase.TestBase;
import com.restful.booker.utils.PropertyReader;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class DeleteBookingAssertion extends TestBase {
    static ValidatableResponse response;
    static int bookingId;
    String getBooking = PropertyReader.getInstance().getProperty("getBooking");

    @BeforeClass
    public void createBooking() {

        //Creating a has map for the POST body. and passing it
        //To do: try to move data to PoJo class and use getter and setter method here
        HashMap data = new HashMap();
        data.put("firstname", "Annu");
        data.put("lastname", "Som");
        data.put("totalprice", 123);
        data.put("depositpaid", true);

        HashMap<String, String> bookingdates = new HashMap<>() {{
            put("checkin", "2018-01-01");
            put("checkout", "2019-01-01");
        }};
        data.put("bookingdates", bookingdates);
        data.put("additionalneeds", "Breakfast");


        response = given()
                .auth().basic("admin", "password123")
                .header("Content-Type", "application/json")
                .when()
                .body(data) //passing the hash map of data for body
                .post(getBooking)
                .then();
        bookingId = response.extract().path("bookingid");


    }


    @Test(priority = 1)
    public void deleteBooking() {
        System.out.println("Booking id captured from POST request:" + bookingId);//for debug purpose
        response = given()
                .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .pathParam("id", bookingId)
                .when()
                .delete(getBooking + "/{id}")
                .then();
        //Assertion 1: Validate status code
        response.statusCode(201); //Delete is showing status 201!
        System.out.println("Assertion 1: Status code for deletion validated.");
    }


    //Asserting of deletion by trying to fetch the booking for deleted id
    //Expecting 404 status code
    @Test(priority = 2)
    public void getBookingById() {

        response = given()
                .pathParam("id", bookingId)
                .when()
                .get("booking/{id}")
                .then();

        //Assertion 2: validate status code of response
        response.statusCode(404); //404 expected as it is validating deletion.
        System.out.println("Assertion 2: status code 404 validated.Record deleted");

    }
}
