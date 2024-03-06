package com.qa.api.tests;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.RequestOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Map;

public class GetAPICall {
    Playwright playwright;
    APIRequest apiRequest;
    APIRequestContext requestContext;

    @BeforeTest
    public void setup(){
        playwright = Playwright.create();
        apiRequest = playwright.request();
        requestContext = apiRequest.newContext();
    }

    @AfterTest
    public void tearDown(){
        playwright.close();
    }

    @Test
    public void getSpecificUserApiTest(){
        APIResponse apiResponse = requestContext.get("https://gorest.co.in/public/v2/users",
                RequestOptions.create()
                        .setQueryParam("gender", "male")
                        .setQueryParam("status", "inactive"));

        int statusCode = apiResponse.status();
        System.out.println("Response status code: " + statusCode);
        Assert.assertEquals(statusCode, 200);
        Assert.assertTrue(apiResponse.ok());

        String statusResTest = apiResponse.statusText();
        System.out.println("Response status text: " + statusResTest);

        System.out.println("----- print api response with plain text -----");
        System.out.println(apiResponse.text());
    }
    
    @Test
    public void getUsersApiTest(){
        APIResponse apiResponse = requestContext.get("https://gorest.co.in/public/v2/users");

        int statusCode = apiResponse.status();
        System.out.println("Response status code: " + statusCode);
        Assert.assertEquals(statusCode, 200);
        Assert.assertTrue(apiResponse.ok());

        String statusResTest = apiResponse.statusText();
        System.out.println("Response status text: " + statusResTest);

        System.out.println("----- print api response with plain text -----");
        System.out.println(apiResponse.text());

        try {
            apiResponse.body();
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonResponse = objectMapper.readTree(apiResponse.body());
            String jsonPrettyResponse = jsonResponse.toPrettyString();
            System.out.println(jsonPrettyResponse);
        }
        catch(Exception e) {
            System.out.println("Error --- " + e.getMessage());
        }

        System.out.println("----- print api url -----");
        System.out.println(apiResponse.url());

        System.out.println("----- print response headers -----");
        Map<String, String> headersMap = apiResponse.headers();
        System.out.println(headersMap);
        Assert.assertEquals(headersMap.get("content-type"), "application/json; charset=utf-8");
        Assert.assertEquals(headersMap.get("x-download-options"), "noopen");
    }
}
