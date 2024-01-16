package com.restful.booker.testbase;

import com.restful.booker.utils.PropertyReader;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public class TestBase {
    String baseUri= PropertyReader.getInstance().getProperty("baseUri");

    @BeforeClass
    public void inIt(){
        RestAssured.baseURI=baseUri;
    }

}
