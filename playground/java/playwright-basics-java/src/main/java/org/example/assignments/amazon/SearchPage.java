package org.example.assignments.amazon;

import com.microsoft.playwright.Page;

public class SearchPage {

    public Page page;

    // POM Objects
    String searchBarId = "#twotabsearchtextbox";

    public SearchPage(Page page) { this.page = page; }

    // POM Actions
    public void enterValueToSearch(String value) {
        page.locator(searchBarId).fill(value);
        page.press(searchBarId, "Enter");

        page.waitForTimeout(5000);

    }
}
