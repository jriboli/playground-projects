package com.binaryNomad.tests;

import com.binaryNomad.endpoints.TwitterAPI;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;

public class Twitter_RequestTesting extends TwitterAPI {

    @Test
    public void testUserData() {
        Response res = getUserData("naroken");

        // Using a Soft Assertion with .body()
        res.then()
                .statusCode(200)
                .rootPath("data")
                .body("name", equalTo("Justin"),
                        "username", equalTo("Naroken"));
    }

    @Test(dependsOnMethods = {"testUserData"})
    public void testUserTweetCount() {
        Response res = getTweetCount("from:naroken", "day");

        res.then().statusCode(200);
    }

    @Test
    public void testGetTweetByQuery() {
        Response res = getTweets("fortnite");

        res.then().statusCode(200);
    }
}
