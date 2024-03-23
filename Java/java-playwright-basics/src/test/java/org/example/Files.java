package org.example;

import com.microsoft.playwright.*;
import org.testng.annotations.Test;

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

    }
}
