package com.tests;

import org.testng.annotations.*;

@Test()
public class TestNGBasics {

    @BeforeSuite
    public void bSuite() {
        System.out.println("Before Suite");
    }

    @BeforeTest
    public void bTest() {
        System.out.println("Before Test");
    }

    @BeforeGroups("basic")
    public void bGroups() {
        System.out.println("Before Groups");
    }

    @BeforeClass
    public void bClass(){
        System.out.println("Before Class");
    }

    @BeforeMethod
    public void bMethod() {
        System.out.println("Before Method");
    }

    /// THE TESTING *************************************************

    /***
     * Priority defaults to 0/zero - adding it only controlls the order for ones
     * with the annotation
     *
     *
     */
    @Test(priority=2, groups="basic")
    public void testOne() {
        System.out.println("Test 1");
    }

    @Test(groups="basic")
    public void testTwo() {
        System.out.println("Test 2");
    }

    @Test(groups="advanced")
    public void testThree() {
        System.out.println("Test 3");
    }

    @Test(priority=1)
    public void testFour() {
        System.out.println("Test 4");
    }

    @AfterMethod
    public void aMethod(){
        System.out.println("After Method");
    }

    @AfterClass
    public void aClass() {
        System.out.println("After Class");
    }

    @AfterGroups("basic")
    public void aGroups() {
        System.out.println("After Groups");
    }

    @AfterTest
    public void aTest() {
        System.out.println("After Test");
    }

    @AfterSuite
    public void aSuite() {
        System.out.println("After Suite");
    }

}
