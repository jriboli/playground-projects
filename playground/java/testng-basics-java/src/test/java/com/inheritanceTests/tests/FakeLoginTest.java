package com.inheritanceTests.tests;

import com.inheritanceTests.base.BaseTest;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class FakeLoginTest extends BaseTest {

    @BeforeClass
    public void config() {
        System.out.println("Before Class");
    }

    @Test
    public void login() {
        System.out.println("Step value from Parent Class : " + step);
    }

    @AfterClass
    public void reporting() {
        System.out.println("After Class");
    }
}
