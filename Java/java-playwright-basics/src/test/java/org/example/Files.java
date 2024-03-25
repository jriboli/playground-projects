package org.example;

import com.microsoft.playwright.*;
import org.testng.annotations.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Files {

    @Test
    public void uploadSingleFileTest() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

        Page page = browser.newPage();
        page.navigate("https://www.way2automation.com/way2auto_jquery/registration.php#load_box");

        Locator uploadFile = page.locator("//input[@type='file']");
        uploadFile.setInputFiles(Paths.get("./src/test/resources/testFiles/unnamed.jpg"));

        System.out.println("----" + uploadFile.getAttribute("name"));

        page.waitForTimeout(5000);

    }

    @Test
    public void uploadMultipleFilesTest() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

        Page page = browser.newPage();
        page.navigate("https://www.w3schools.com/jsref/tryit.asp?filename=tryjsref_fileupload_multiple");

        Locator uploadFile = page.locator("//input[@type='file']");
        uploadFile.setInputFiles(new Path[] {
            Paths.get("./src"),
            Paths.get("")
        });

        System.out.println("----" + uploadFile.getAttribute("name"));

        page.waitForTimeout(5000);
    }

    @Test
    public void downloadFileTest() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

        Page page = browser.newPage();
        page.navigate("selenium.dev/downloads");

        // /html/body/div/main/div[4]/div[2]/div/div/p[1]/a
        Download dlFile = page.waitForDownload(() -> {
            page.locator("/html/body/div/main/div[4]/div[2]/div/div/p[1]/a").click();
        });

        dlFile.saveAs(Paths.get("./src/test/resources/testFiles/seleniumServer.jar"));
    }
}
