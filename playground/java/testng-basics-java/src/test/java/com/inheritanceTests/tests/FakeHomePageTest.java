package com.inheritanceTests.tests;

import com.inheritanceTests.base.BaseTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class FakeHomePageTest extends BaseTest {

    @BeforeTest
    public void addlSetup() {
        System.out.println("Additional Setup");
    }

    @Test
    public void homePageTitle() {
        System.out.println("Title Matches");
    }

    @AfterTest
    public void specialTearDown() {
        System.out.println("Special Tear Down");
    }
}
