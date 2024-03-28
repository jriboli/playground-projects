package org.example.playwright;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.testng.annotations.Test;

public class JavascriptActions {

    @Test
    public void javaScriptTest() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

        Page page = browser.newPage();
        page.navigate("http://google.com");

        System.out.println(page.evaluate("document.location.href"));

        page.evaluate("() => {"
                + "const textarea = document.createElement('textarea');"
                + "document.body.append(textarea);"
                + "textarea.focus();"
                + "}");

        String text = "Hello World!";
        page.keyboard().type(text);
    }

    @Test
    public void keyBoardTest() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

        Page page = browser.newPage();
        page.navigate("https://login.yahoo.com");

        page.locator("#login-username").fill("rraccoon1990@yahoo.com");
        page.keyboard().press("Enter");

        page.goBack();
        page.keyboard().press("Control+A");
        page.keyboard().press("Control+X");
        page.keyboard().press("Control+V");
    }
}
