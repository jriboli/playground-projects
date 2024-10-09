package com.binaryNomad.endpoints;

import com.binaryNomad.utils.EnvUtils;
import com.binaryNomad.utils.RestRequestBuilder;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;

public class TwitterAPI {
    private static final String CONSUMER_KEY = EnvUtils.getEnv("TWITTER_CONSUMER_KEY");
    private static final String CONSUMER_SECRET = EnvUtils.getEnv("TWITTER_CONSUMER_SECRET");
    private static final String ACCESS_TOKEN = EnvUtils.getEnv("TWITTER_ACCESS_TOKEN");
    private static final String ACCESS_TOKEN_SECRET = EnvUtils.getEnv("TWITTER_ACCESS_TOKEN_SECRET");

    private static final String TWITTER_TOKEN_URL = "https://api.twitter.com/oauth2/token";

    String baseEndpoint = "https://api.x.com/2/";
    String authToken;

    public TwitterAPI() {
        authToken = generateOAuth2Token();
    }

    public Response getUserData(String username) {
        String fullEndpoint = baseEndpoint + "users/by/username/" + username;
        //return RestRequestBuilder.performGet(authReq, fullEndpoint, new HashMap<>());
        return new RestRequestBuilder()
                .withEndpoint(fullEndpoint)
                //.withOAuth1(CONSUMER_KEY, CONSUMER_SECRET, ACCESS_TOKEN, ACCESS_TOKEN_SECRET)
                .withOAuth2(authToken)
                .performGet();
    }

    public Response getTweetCount(String query, String granularity) {
        // Granularity: Possible values are day, hour or minute.
        String fullEndpoint = baseEndpoint + "tweets/counts/recent";

        // Query Parameters
        HashMap<String, String> queryParams = new HashMap<>();
        queryParams.put("query", query);
        queryParams.put("granularity", granularity);

        //return RestRequestBuilder.performGet(authReq, fullEndpoint, queryParams);
        return new RestRequestBuilder()
                .withEndpoint(fullEndpoint)
                //.withOAuth1(CONSUMER_KEY, CONSUMER_SECRET, ACCESS_TOKEN, ACCESS_TOKEN_SECRET)
                .withOAuth2(authToken)
                .performGet();
    }

    public Response getTweets(String query) {
        String fullEndpoint = baseEndpoint + "tweets/search/recent";

        // Query Parameters
        HashMap<String, String> queryParams = new HashMap<>();
        queryParams.put("query", query);

        //return RestRequestBuilder.performGet(authReq, fullEndpoint, queryParams);
        return new RestRequestBuilder()
                .withEndpoint(fullEndpoint)
                //.withOAuth1(CONSUMER_KEY, CONSUMER_SECRET, ACCESS_TOKEN, ACCESS_TOKEN_SECRET)
                .withOAuth2(authToken)
                .performGet();
    }

    private String generateOAuth2Token() {
        Response res = RestAssured.given().log().all()
                .auth().preemptive().basic(CONSUMER_KEY, CONSUMER_SECRET)
                .contentType(ContentType.URLENC)
                .formParam("grant_type", "client_credentials")
                .post(TWITTER_TOKEN_URL);

        return res.jsonPath().getString("access_token");
    }

}
