package org.example.playwright;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.testng.annotations.Test;

public class TestGenerator {

    // Running the following command from the Project root level ( same as pom.xml )
    // mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="codegen demo.playwright.dev/todomvc"
    @Test
    public void GenWikipediaTest() {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                    .setHeadless(false));
            BrowserContext context = browser.newContext();
            Page page = context.newPage();
            page.navigate("https://www.wikipedia.org/");
            page.getByLabel("Search Wikipedia").click();
            page.getByLabel("Search Wikipedia").fill("Star Lord");
            page.getByLabel("Search Wikipedia").press("Enter");
            page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Star-Lord").setExact(true)).locator("span").click();
        }
    }
}
