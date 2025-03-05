package com.inheritanceTests.base;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseTest {
    protected String step = "";

    @BeforeTest
    public void setup() {
        step = "Before Test";
        System.out.println("Current step: " + step);
    }

    @AfterTest
    public void tearDown() {
        step = "After Test";
        System.out.println("Current step: " + step);
    }
}
