package com.binaryNomad.tests;

import com.binaryNomad.endpoints.TwitterAPI;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class Twitter_Testing extends TwitterAPI {

    @Test
    public void testUserData() {
        Response res = getUserData("naroken");

        res.then().statusCode(200);
    }

    @Test
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
