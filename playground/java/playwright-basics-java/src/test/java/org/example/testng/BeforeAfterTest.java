package org.example.testng;

import org.testng.annotations.*;

public class BeforeAfterTest {

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
        System.out.println("Test - Priority: 2 - Group: Basic");
    }

    @Test(groups="basic")
    public void testTwo() {
        System.out.println("Test - Group: Basic");
    }

    @Test(groups="advanced")
    public void testThree() {
        System.out.println("Test - Group: Advanced");
    }

    /***
     * TestNG will always follow priority on selected tests, and groups
     * are a way to change the test selection.
     */
    @Test(priority=1, groups="misc")
    public void testFour() {
        System.out.println("Test - Priority: 1 - Group: Misc");
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
    public void aTest() { System.out.println("After Test"); }

    @AfterSuite
    public void aSuite() { System.out.println("After Suite"); }
}
