package com.saucedemo.factory;

import com.microsoft.playwright.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PlaywrightFactory {

    Playwright playwright;
    Browser browser;
    BrowserContext browserContext;
    Page page;

    Properties prop;

    //Initialize
    public Page initBrowser(Properties prop) {
        return initBrowser(prop, new BrowserType.LaunchOptions().setHeadless(false));
    }
    public Page initBrowser(Properties prop, BrowserType.LaunchOptions options) {

        String browserName = prop.getProperty("browser").trim();
        System.out.println("Browser name is : " + browserName);
        System.out.println("Page URL is: " + prop.getProperty("url").trim());

        playwright = Playwright.create();
        switch(browserName.toLowerCase()) {
            case "chromium":
                browser = playwright.chromium().launch(options);
                break;
            case "firefox":
                browser = playwright.firefox().launch(options);
                break;
            case "safari":
                browser = playwright.webkit().launch(options);
                break;
            case "chrome":
                browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false));
                break;
            default:
                System.out.println("Please pass a browser name...");
                break;
        }

        browserContext = browser.newContext();
        page = browserContext.newPage();
        page.navigate(prop.getProperty("url").trim());

        return page;
    }

    public Properties init_prop() {
        try {
            FileInputStream ip = new FileInputStream("./src/test/resources/config/config.properties");
            prop = new Properties();
            prop.load(ip);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return prop;
    }
}
