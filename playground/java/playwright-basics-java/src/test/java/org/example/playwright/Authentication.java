package org.example.playwright;

import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Response;

public class Authentication {
    
    @Test
    public void authenticationTest() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext browserContext = browser.newContext(new Browser.NewContextOptions().setHttpCredentials("admin", "admin"));
        Page page = browserContext.newPage();
        
        Response response = page.navigate("http://the-internet.herokuapp.com/basic_auth");
        System.out.println("Site response code: " + response.status());

        page.waitForTimeout(5000);
    }

    @Test
    public void authenticationInvalidTest() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext browserContext = browser.newContext(new Browser.NewContextOptions().setHttpCredentials("admin", "password"));
        Page page = browserContext.newPage();

        Response response = page.navigate("http://the-internet.herokuapp.com/basic_auth");
        System.out.println("Site response code: " + response.status());

        page.waitForTimeout(5000);
    }
}
