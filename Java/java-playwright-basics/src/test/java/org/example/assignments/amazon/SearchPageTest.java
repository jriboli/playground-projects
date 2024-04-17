package org.example.assignments.amazon;

import org.example.assignments.AmazonBaseTest;
import org.testng.annotations.Test;

public class SearchPageTest extends AmazonBaseTest {

    @Test
    public void searchForItem() {
        searchPage.enterValueToSearch("IPhone 14");
    }
}
