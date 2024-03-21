package org.example;

import com.microsoft.playwright.*;
import org.testng.annotations.Test;

import java.awt.*;
import java.nio.file.Paths;
import java.util.ArrayList;

public class LaunchBrowser {

    @Test
    public void LaunchBrowserTest() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();

        page.navigate("http://way2automation.com");

        System.out.println("Page Title: " + page.title());

        //page.close();
        playwright.close();
    }

    @Test
    public void MaximizeTheBrowserTest() {
        // Dynamically get the max screen size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        //BrowserContext browserContext = browser.newContext(new Browser.NewContextOptions().setViewportSize(1920, 929));
        BrowserContext browserContext = browser.newContext(new Browser.NewContextOptions().setViewportSize((int)width, (int)height));
        Page page = browserContext.newPage();

        page.navigate("http://way2automation.com");

        System.out.println("Page Title: " + page.title());

        //page.close();
        playwright.close();
    }

    @Test
    public void MaximizeTheBrowserBonusTest() {
        Playwright playwright = Playwright.create();
        ArrayList<String> arguments = new ArrayList<>();
        arguments.add("--start-maximized");
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setArgs(arguments));
        BrowserContext browserContext = browser.newContext(new Browser.NewContextOptions().setViewportSize(null));
        Page page = browserContext.newPage();

        page.navigate("http://way2automation.com");

        System.out.println("Page Title: " + page.title());

        //page.close();
        playwright.close();
    }

    @Test
    public void LaunchFirefoxBrowserTest() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();

        page.navigate("http://way2automation.com");

        System.out.println("Page Title: " + page.title());

        //page.close();
        playwright.close();
    }

    @Test
    public void LaunchNonIncognitoTest() {
        Playwright playwright = Playwright.create();
        //Browser browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext browserContext = playwright
                .chromium()
                .launchPersistentContext(Paths.get(""), new BrowserType.LaunchPersistentContextOptions().setHeadless(false));

        Page page = browserContext.newPage();

        page.navigate("http://way2automation.com");

        System.out.println("Page Title: " + page.title());

        //page.close();
        playwright.close();
    }

    @Test
    public void BrowserNavigationTest() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();

        page.navigate("http://way2automation.com");
        System.out.println("Page Title: " + page.title());

        page.navigate("http://google.com");
        page.goBack();

        page.goForward();

        page.reload();

        //page.close();
        playwright.close();
    }


}
