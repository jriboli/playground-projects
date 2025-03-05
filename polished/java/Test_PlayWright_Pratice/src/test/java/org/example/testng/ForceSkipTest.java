package org.example.testng;

import org.testng.SkipException;
import org.testng.annotations.Test;

public class ForceSkipTest {

    @Test
    public void isSkip() {

        throw new SkipException("Skipping the test as condition is not met");
    }

    @Test
    public void isSkip2() {

    }
}
