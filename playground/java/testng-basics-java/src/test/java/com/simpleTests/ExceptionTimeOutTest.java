package com.simpleTests;

import org.testng.annotations.Test;

public class ExceptionTimeOutTest {

    @Test(timeOut = 2000) // In milliseconds
    public void infiniteLoopTest() {
        int i = 1;
        while (i==1) {
            System.out.println(i);
        }
    }
}
