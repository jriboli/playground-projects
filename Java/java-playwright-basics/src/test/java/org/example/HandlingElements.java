package org.example;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.testng.annotations.Test;

public class HandlingElements {

    @Test
    public void FirstSelector() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

        Page page = browser.newPage();
        page.navigate("https://www.wikipedia.org/");

        // Using CSS selector
        //page.locator("#searchInput").fill("Rocket Raccoon");

        // Using element attribute
        //page.locator("[id=\"searchInput\"]").fill("Playwright");
        //page.locator("[type=\"search\"]").fill("Gamora");

        // Using Fill directly
        page.fill("#searchInput", "Star Lord");

        // Using element attribute selector
        page.click("[type=submit]");

        // Using another route
        //page.click("button:has-text('')");

        //playwright.close();
    }

    @Test
    public void TheLocator() throws InterruptedException {
        // Locator waits for the element to be actionable. They play a piece in auto-waiting and retry-ability.
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

        Page page = browser.newPage();
        page.navigate("https://www.wikipedia.org/");

        page.locator("#searchInput").fill("Star Lord");
        page.locator("[type=submit]").click();

        Thread.sleep(1000);

        String pageTitle = page.locator(".mw-page-title-main").innerText();
        System.out.println("Page Title: " + pageTitle);
    }
}
