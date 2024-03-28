package org.example.testng;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Listener {

    @Test
    public void ListenerTest() {

        Assert.fail("Failing on purpose");

    }
}
