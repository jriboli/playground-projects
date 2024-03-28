package org.example.playwright;

import com.microsoft.playwright.*;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

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

    @Test
    public void webTableTest() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

        Page page = browser.newPage();
        page.navigate("https://money.rediff.com/indices/nse/NIFTY-50?src=moneyhome_nseIndices");

        // ROW Count
        System.out.println(page.locator(".dataTable > tbody").locator("tr").count());
        // COLUMN Count
        System.out.println(page.locator(".dataTable > tbody").locator("tr:first-child").locator("td").count());

        assertThat(page.locator(".dataTable > tbody").locator("tr:first-child").locator("td:nth-child(2)")).hasText("Nifty");

        // PRINT Data from Table
        page.locator(".dataTable > tbody").allInnerTexts().forEach(text -> System.out.println(text));


    }
}
