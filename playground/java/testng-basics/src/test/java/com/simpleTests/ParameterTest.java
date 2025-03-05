package com.simpleTests;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ParameterTest {

    // Run with testing_parameters TestRunner
    @Test
    @Parameters({"url", "emailId"})
    public void yahooLoginTest(String url, String emailId) {
        System.out.println("URL : " + url);
        System.out.println("EmailId : " + emailId);
    }
}
