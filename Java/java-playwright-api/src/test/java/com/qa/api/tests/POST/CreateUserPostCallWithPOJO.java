package com.qa.api.tests.POST;

import com.api.data.Users;
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

import java.util.HashMap;
import java.util.Map;

public class CreateUserPostCallWithPOJO {
    Playwright playwright;
    APIRequest apiRequest;
    APIRequestContext requestContext;

    public static String getRandomEmail(){
        String emailId = "rraccoon"+System.currentTimeMillis() +"@gmail.com";
        return emailId;
    }

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
    public void createUserTest() {

        //Create users object: using builder pattern
        Users userData = Users.builder()
                .name("Rocket Raccoon")
                .email(getRandomEmail())
                .gender("male")
                .status("active").build();

        //POST Call: create a user
        APIResponse apiResponse = requestContext.post("https://gorest.co.in/public/v2/users",
                RequestOptions.create()
                        .setHeader("context-type", "application/json")
                        .setHeader("Authorization", "Bearer 9a32c88468523f9c150a5665a0595bdc782d425a250ef6f6d758ed62d5934024")
                        .setData(userData));

        int statusCode = apiResponse.status();
        System.out.println("Response status code: " + statusCode);
        Assert.assertEquals(statusCode, 201);
        Assert.assertTrue(apiResponse.ok());

        String statusResTest = apiResponse.statusText();
        System.out.println("Response status text: " + statusResTest);
        Assert.assertEquals(statusResTest, "Created");

        System.out.println("----- print api response with plain text -----");
        System.out.println(apiResponse.text());

        int userId = 0;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Users userResp = objectMapper.readValue(apiResponse.text(), Users.class);
            System.out.println("----- print users object -----");
            System.out.println(userResp);
            userId = userResp.getId();
        }
        catch(Exception e) {
            System.out.println("Error --- " + e.getMessage());
        }

        System.out.println("----- api fetch user response -----");
        APIResponse apiFetchResponse = requestContext.get("https://gorest.co.in/public/v2/users/" + userId,
                RequestOptions.create()
                        .setHeader("Authorization", "Bearer 9a32c88468523f9c150a5665a0595bdc782d425a250ef6f6d758ed62d5934024"));

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonResponse = objectMapper.readTree(apiFetchResponse.body());
            String jsonPrettyResponse = jsonResponse.toPrettyString();
            System.out.println(jsonPrettyResponse);
        }
        catch(Exception e) {
            System.out.println("Error --- " + e.getMessage());
        }
    }
}
