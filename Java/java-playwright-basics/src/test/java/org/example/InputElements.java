package org.example;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.SelectOption;
import org.testng.annotations.Test;

import java.util.List;

public class InputElements {

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

    @Test
    public void dropDownTest() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

        Page page = browser.newPage();
        page.navigate("https://www.wikipedia.org/");

        // Select by Value
        page.selectOption("#searchLanguage", "es");

        // Select by Text
        page.selectOption("#searchLanguage", new SelectOption().setLabel("Polski"));

        // Select by Index
        page.selectOption("#searchLanguage", new SelectOption().setIndex(1));

        //
        Locator values = page.locator("select > option");
        System.out.println("Number of supported Languages: " + values.count());

        for(int i=0; i< values.count(); i++) {
            System.out.println(values.nth(i).innerText() + "-----" + values.nth(i).getAttribute("lang"));
        }

        // Multiple elements
        List<ElementHandle> pageValues = page.querySelectorAll("select > option");

        for(ElementHandle value : pageValues) {
            System.out.println(value.innerText() + "-----" + value.getAttribute("lang"));
        }

        playwright.close();
    }

    @Test
    public void linkTest(){
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

        Page page = browser.newPage();
        page.navigate("https://www.wikipedia.org/");

        Locator links = page.locator("a");

        System.out.println("Num of Links: " + links.count());

        for(int i = 0; i < links.count(); i++){
            System.out.println(links.nth(i).innerText() + "-----" + links.nth(i).getAttribute("href"));
        }
    }

    @Test
    public void linksWithinElementTest() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

        Page page = browser.newPage();
        page.navigate("https://www.wikipedia.org/");

        Locator block = page.locator("//*[@id=\"www-wikipedia-org\"]/footer/nav");
        Locator blockLinks = block.locator("a");

        System.out.println("Num of Links: " + blockLinks.count());

        for(int i = 0; i < blockLinks.count(); i++){
            System.out.println(blockLinks.nth(i).innerText() + "-----" + blockLinks.nth(i).getAttribute("href"));
        }
    }

    @Test
    public void checkBoxesTest() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

        Page page = browser.newPage();
        page.navigate("http://www.tizag.com/htmlT/htmlcheckboxes.php");

        Locator block = page.locator("(//h2[@class='specialT']/following-sibling::div)[1]");
        Locator checkboxes = block.locator("[type='checkbox']");

        for(int i = 0; i < checkboxes.count(); i++) {
            checkboxes.nth(i).click();
            System.out.println(checkboxes.nth(i).getAttribute("value"));
        }

        playwright.close();
    }
}
