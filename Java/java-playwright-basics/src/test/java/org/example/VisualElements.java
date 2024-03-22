package org.example;

import com.microsoft.playwright.*;
import org.testng.annotations.Test;

public class VisualElements {

    @Test
    public void firstAlertTest() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

        Page page = browser.newPage();
        page.navigate("https://mail.rediff.com/cgi-bin/login.cgi");

        page.onDialog(dialog -> {
            dialog.accept();
            System.out.println(dialog.message());
        });

        page.locator("[name='proceed']").click();
    }

    @Test
    public void theFrameTest() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

        Page page = browser.newPage();
        page.navigate("https://www.w3schools.com/jsref/tryit.asp?filename=tryjsref_submit_get");

        FrameLocator frame = page.frameLocator("[name='iframeResult']");
        frame.locator("body > button").click(new Locator.ClickOptions().setTimeout(5000));

        String result = frame.locator("[id=\"demo\"]").innerText();
        System.out.println("Results: " + result);
    }

    @Test
    public void tabsAndPopupsTest() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

        Page page = browser.newPage();
        page.navigate("https://sso.teachable.com/secure/673/identity/sign_up/email");

        Page popup = page.waitForPopup(() -> {
            page.locator("text=Privacy Policy").nth(0).click();
        });
    }
}
