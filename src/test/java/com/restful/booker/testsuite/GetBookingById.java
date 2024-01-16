package com.restful.booker.testsuite;

import com.restful.booker.testbase.TestBase;
import com.restful.booker.utils.PropertyReader;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetBookingById extends TestBase {
    String getBooking = PropertyReader.getInstance().getProperty("getBooking");
    static ValidatableResponse response;
    static List<Integer> bookingIds;

    @BeforeClass
    public void getBookings() {
        response = given()
                .when()
                .get(getBooking)
                .then();
        response.statusCode(200);

        bookingIds = response.extract().path("bookingid");
    }

    @Test
    public void getBookingById() {

        //get a random number for up to maximum arraylist size
        int randomNum = (int) (Math.random() * bookingIds.size() - 1);
        //Use the random number generated to pick a random booking id from array list
        int randomIndex = bookingIds.get(randomNum);
        response = given()
                .pathParam("id", randomIndex)
                .when()
                .get("booking/{id}")
                .then();

        //Assertion 1: validate status code of response
        response.statusCode(200);
        //***Getting booking for random booking id generated***
        response.log().all();

        //Assertion 2: validate response body is not empty
        Map<String,?> bookingDetails=response.extract().path("$");
        Assert.assertTrue(!bookingDetails.isEmpty());
        System.out.println("Assertion 1: Status code of response validated.");
        System.out.println("Assertion 2: Response body not empty.");

    }



}
