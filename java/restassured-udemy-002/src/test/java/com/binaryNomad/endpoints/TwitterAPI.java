package com.binaryNomad.endpoints;

import com.binaryNomad.utils.EnvUtils;
import com.binaryNomad.utils.RestRequestBuilder;
import io.restassured.response.Response;

import java.util.HashMap;

public class TwitterAPI {
    private static final String CONSUMER_KEY = EnvUtils.getEnv("TWITTER_CONSUMER_KEY");
    private static final String CONSUMER_SECRET = EnvUtils.getEnv("TWITTER_CONSUMER_SECRET");
    private static final String ACCESS_TOKEN = EnvUtils.getEnv("TWITTER_ACCESS_TOKEN");
    private static final String ACCESS_TOKEN_SECRET = EnvUtils.getEnv("TWITTER_ACCESS_TOKEN_SECRET");
    private static final String TOKEN_URL = "https://api.twitter.com/oauth2/token";
    private static final String BASE_URL = "https://api.x.com/2/";

    public Response getUserData(String username) {
        String fullEndpoint = BASE_URL + "users/by/username/" + username;
        return new RestRequestBuilder()
                .withEndpoint(fullEndpoint)
                .withOAuth2(CONSUMER_KEY, CONSUMER_SECRET, TOKEN_URL)
                .performGet();
    }

    public Response getTweetCount(String query, String granularity) {
        // Granularity: Possible values are day, hour or minute.
        String fullEndpoint = BASE_URL + "tweets/counts/recent";

        // Query Parameters
        HashMap<String, String> queryParams = new HashMap<>();
        queryParams.put("query", query);
        queryParams.put("granularity", granularity);

        return new RestRequestBuilder()
                .withEndpoint(fullEndpoint)
                .withQueryParams(queryParams)
                .withOAuth2(CONSUMER_KEY, CONSUMER_SECRET, TOKEN_URL)
                .performGet();
    }

    public Response getTweets(String query) {
        String fullEndpoint = BASE_URL + "tweets/search/recent";

        // Query Parameters
        HashMap<String, String> queryParams = new HashMap<>();
        queryParams.put("query", query);

        return new RestRequestBuilder()
                .withEndpoint(fullEndpoint)
                .withQueryParams(queryParams)
                .withOAuth2(CONSUMER_KEY, CONSUMER_SECRET, TOKEN_URL)
                .performGet();
    }

}
