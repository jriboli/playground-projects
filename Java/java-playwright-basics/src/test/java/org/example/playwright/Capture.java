package org.example.playwright;

import com.microsoft.playwright.*;
import java.nio.file.Paths;
import org.testng.annotations.Test;

public class Capture {

    @Test
    public void screenCaptureTest() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

        Page page = browser.newPage();
        page.navigate("https://www.w3schools.com/jsref/tryit.asp?filename=tryjsref_submit_get");

        FrameLocator frame = page.frameLocator("[name='iframeResult']");
        frame.locator("body > button").click(new Locator.ClickOptions().setTimeout(5000));
        frame.locator("body > button").screenshot(new Locator.ScreenshotOptions().setPath(Paths.get("./screenshots/screenshotOfElement.jpg")));

        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("./screenshots/screenshot.jpg")));

        String result = frame.locator("[id=\"demo\"]").innerText();
        System.out.println("Results: " + result);
    }

    @Test
    public void videoCaptureTest() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext browserContext = browser.newContext(new Browser.NewContextOptions().setRecordVideoDir(Paths.get("videos/")));

        Page page = browserContext.newPage();
        page.navigate("https://www.w3schools.com/jsref/tryit.asp?filename=tryjsref_submit_get");

        FrameLocator frame = page.frameLocator("[name='iframeResult']");
        frame.locator("body > button").click(new Locator.ClickOptions().setTimeout(5000));

        String result = frame.locator("[id=\"demo\"]").innerText();
        System.out.println("Results: " + result);

        browserContext.close();
    }

    @Test
    public void traceViewerTest() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext context = browser.newContext();

        context.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(false));

        Page page = context.newPage();
        page.navigate("http://gmail.com");
        page.locator("[type=email]").fill("rraccoon1990@gmail.com");
        page.click("button:has-text('Next')");

        context.tracing().stop(new Tracing.StopOptions()
                .setPath(Paths.get("traces/trace.zip")));

        // mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="show-trace trace.zip"
    }
}
