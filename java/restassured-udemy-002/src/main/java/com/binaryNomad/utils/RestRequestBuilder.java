package com.binaryNomad.utils;

import com.binaryNomad.enums.AuthType;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.basic.DefaultOAuthConsumer;

import java.util.HashMap;
import java.util.Map;

public class RestRequestBuilder {

    private String endpoint;
    private Map<String, String> queryParams = new HashMap<>();
    private Map<String, String> headers = new HashMap<>();
    private Object requestPayload;
    private boolean enableUrlEncoding = true;
    private AuthType authType = AuthType.NONE;

    // Fields for different authentication methods
    private String basicAuthUsername;
    private String basicAuthPassword;

    private String consumerKey;
    private String consumerSecret;
    private String accessToken;
    private String tokenSecret;

    private String bearerToken;
    private String apiKey;
    private String apiSecret;
    private String tokenURL;

    public RestRequestBuilder withEndpoint(String endpoint) {
        this.endpoint = endpoint;
        return this;
    }

    public RestRequestBuilder withQueryParams(Map<String, String> queryParams) {
        this.queryParams = queryParams;
        return this;
    }

    public RestRequestBuilder withHeaders(Map<String, String> headers) {
        this.headers = headers;
        return this;
    }

    public RestRequestBuilder withRequestBody(Object requestPayload) {
        this.requestPayload = requestPayload;
        return this;
    }

    public RestRequestBuilder enableUrlEncoding(boolean enableUrlEncoding) {
        this.enableUrlEncoding = enableUrlEncoding;
        return this;
    }

    // Step 2: Setters for Authentication methods
    // Basic Authentication
    public RestRequestBuilder withBasicAuth(String username, String password) {
        this.basicAuthUsername = username;
        this.basicAuthPassword = password;
        this.authType = AuthType.BASIC;
        return this;
    }

    // OAuth 1.0 Authentication
    public RestRequestBuilder withOAuth1(String consumerKey, String consumerSecret, String accessToken, String tokenSecret) {
        this.consumerKey = consumerKey;
        this.consumerSecret = consumerSecret;
        this.accessToken = accessToken;
        this.tokenSecret = tokenSecret;
        this.authType = AuthType.OAUTH1;
        return this;
    }

    // OAuth 2.0 Bearer Token Authentication
    public RestRequestBuilder withOAuth2(String apiKey, String apiSecret, String tokenURL) {
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
        this.tokenURL = tokenURL;
        this.authType = AuthType.OAUTH2;
        return this;
    }

    // Step 3: Method to apply authentication based on the chosen type
    private void applyAuthentication() {
        switch (authType) {
            case BASIC:
                RestAssured.given().auth().basic(basicAuthUsername, basicAuthPassword);
                break;
            case OAUTH1:
                applyOAuth1();
                break;
            case OAUTH2:
                applyOAuth2();
                break;
            case NONE:
                // No authentication required
                break;
        }
    }

    // Helper method to apply OAuth 1.0 headers
    private void applyOAuth1() {
        try {
            OAuthConsumer consumer = new DefaultOAuthConsumer(consumerKey, consumerSecret);
            consumer.setTokenWithSecret(accessToken, tokenSecret);
            // Sign the request (OAuth 1.0 requires the URL and HTTP method to sign properly)
            consumer.sign(endpoint);
            headers.put("Authorization", consumer.getConsumerKey());
        } catch (Exception e) {
            throw new RuntimeException("Failed to apply OAuth 1.0 authentication", e);
        }
    }

    private void applyOAuth2() {
        Response res = RestAssured.given().log().all()
                .auth().preemptive().basic(apiKey, apiSecret)
                .contentType(ContentType.URLENC)
                .formParam("grant_type", "client_credentials")
                .post(tokenURL);

        this.bearerToken = res.jsonPath().getString("access_token");
        headers.put("Authorization", "Bearer " + bearerToken);
    }

    public Response performGet() {
        applyAuthentication();

        return RestAssured.given().log().all().
                urlEncodingEnabled(enableUrlEncoding).
                baseUri(endpoint).
                queryParams(queryParams).
                headers(headers).
                get().
                then().log().all().extract().response();
    }

    public Response performPost() {
        applyAuthentication();

        return RestAssured.given().log().all().
                urlEncodingEnabled(enableUrlEncoding).
                baseUri(endpoint).
                queryParams(queryParams).
                headers(headers).
                contentType(ContentType.JSON).
                body(requestPayload).
                post().
                then().log().all().extract().response();

    }
}
