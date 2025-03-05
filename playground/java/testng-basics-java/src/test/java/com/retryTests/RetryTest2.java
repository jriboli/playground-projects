package com.retryTests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class RetryTest2 {
    int testOneCounter = 0;
    @Test
    public void TestOne() {
        testOneCounter++;
        System.out.println("Result: " + testOneCounter%3);
        Assert.assertEquals(true, testOneCounter%3 == 0);
    }

    @Test
    public void TestTwo() {
        Assert.assertEquals(false, true);
    }
}
