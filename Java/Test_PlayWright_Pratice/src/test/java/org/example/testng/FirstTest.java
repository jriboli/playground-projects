package org.example.testng;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class FirstTest {

    @Test
    public void doLogin() {
        System.out.println("Executing login test");
    }

    @Test
    public void assertionHard() {
        String expectedTitle = "Yahoo";
        String actualTitle = "Google";

        Assert.assertEquals(expectedTitle, actualTitle);
    }

    @Test
    public void assertionSoft() {
        SoftAssert softAssert = new SoftAssert();

        String expectedTitle = "Yahoo";
        String actualTitle = "Google";

        softAssert.assertEquals(expectedTitle, actualTitle);
        softAssert.fail("Failure case");
        softAssert.assertEquals(true, true);

        softAssert.assertAll();

    }
}
