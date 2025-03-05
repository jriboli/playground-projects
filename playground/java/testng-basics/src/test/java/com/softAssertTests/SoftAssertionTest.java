package com.softAssertTests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SoftAssertionTest {

    SoftAssert softAssert = new SoftAssert();
    @Test
    public void TestOne() {
        System.out.println("Open Browser");
        Assert.assertEquals(true, true, "Unable to launch browser");
        // This is a hard assertion and any lines after it will not be executed

        System.out.println("enter username");
        System.out.println("enter password");
        softAssert.assertEquals(true, false, "Login Page missing logo");

        System.out.println("click signon button");
        softAssert.assertEquals(true, true, "Home Page Title missing");

        System.out.println("validate Home Page");

        softAssert.assertAll();
    }

    @Test
    public void TestTwo() {
        System.out.println("logout");
        softAssert.assertEquals(true, false);
        softAssert.assertAll();
    }
}
