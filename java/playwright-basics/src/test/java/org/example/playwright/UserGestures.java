package org.example.playwright;

import com.microsoft.playwright.*;
import org.testng.annotations.Test;

public class UserGestures {

    @Test
    public void mouseOverTest() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

        Page page = browser.newPage();
        page.navigate("https://www.way2automation.com/");

        page.locator("(//span[text()='All Courses'])[1]").hover();
        page.locator("//li[@id='menu-item-27592']/a[1]").click();
    }

    @Test
    public void sliderTest() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

        Page page = browser.newPage();
        page.navigate("https://jqueryui.com/resources/demos/slider/default.html");

        Locator slider = page.locator("#slider");
        page.mouse().move(slider.boundingBox().x + slider.boundingBox().width/2, slider.boundingBox().y + slider.boundingBox().height/2);
        page.mouse().down();
        page.mouse().move(slider.boundingBox().x + 400, slider.boundingBox().y + slider.boundingBox().height/2);
        page.mouse().up();

        Locator sliderValue = page.locator("//div[contains(@class,'ui-slider ui-corner-all')]//span[1]");
        System.out.println(sliderValue.getAttribute("style"));
    }

    @Test
    public void resizableTest() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

        Page page = browser.newPage();
        page.navigate("https://jqueryui.com/resources/demos/resizable/default.html");

        Locator resizeBox = page.locator("//*[@id=\"resizable\"]/div[3]");
        page.mouse().move(resizeBox.boundingBox().x + resizeBox.boundingBox().width/2, resizeBox.boundingBox().y + resizeBox.boundingBox().height/2);
        page.mouse().down();
        page.mouse().move(resizeBox.boundingBox().x + 400, resizeBox.boundingBox().y + 400);
        page.mouse().up();

        page.waitForTimeout(5000);

        Locator resizeBoxValue = page.locator("#resizable");
        System.out.println(resizeBoxValue.getAttribute("style"));
    }

    @Test
    public void dragAndDropTest() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

        Page page = browser.newPage();
        page.navigate("https://jqueryui.com/resources/demos/droppable/default.html");

        Locator item = page.locator("#draggable");
        Locator basket = page.locator("#droppable");

        page.mouse().move(item.boundingBox().x + item.boundingBox().width/2, item.boundingBox().y + item.boundingBox().height/2);
        page.mouse().down();
        page.mouse().move(basket.boundingBox().x + basket.boundingBox().width/2, basket.boundingBox().y + basket.boundingBox().height/2);
        page.mouse().up();

        page.waitForTimeout(2000);

        Locator result = page.locator("//p[text()='Dropped!']");
        System.out.println(result.innerText());

    }

    @Test
    public void domElementsTest() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

        Page page = browser.newPage();
        page.navigate("chrome://downloads/");
        page.locator("#searchInput").fill("Java");
    }
}
