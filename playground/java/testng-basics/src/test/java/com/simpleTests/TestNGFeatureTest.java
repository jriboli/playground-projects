package com.simpleTests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestNGFeatureTest {

    @Test
    public void requiredTest() {
        System.out.println("Required Test");
        Assert.fail();
    }

    @Test(dependsOnMethods = "requiredTest")
    public void TestOne(){
        System.out.println("Test 1");
    }

    @Test(dependsOnMethods = "requiredTest")
    public void TestTwo(){
        System.out.println("Test 2");
    }

    @Test
    public void TestThree(){
        System.out.println("Test 3");
    }
}
