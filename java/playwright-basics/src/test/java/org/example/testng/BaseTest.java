package org.example.testng;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseTest {

    @BeforeTest
    public void setUp() {

        System.out.println("Initializing everything");
    }

    @AfterTest
    public void tearDown() {

        System.out.println("Closing everything");
    }
}
