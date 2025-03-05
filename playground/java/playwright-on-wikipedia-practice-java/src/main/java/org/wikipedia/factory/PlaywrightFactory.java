package org.wikipedia.factory;

import com.microsoft.playwright.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;

public class PlaywrightFactory {
    Playwright playwright;
    Browser browser;
    BrowserContext browserContext;
    Page page;
    Properties prop;

    // Java thread local variables
    private ThreadLocal<Browser> tlBrowser = new ThreadLocal<>();
    private ThreadLocal<BrowserContext> tlBrowserContext = new ThreadLocal<>();
    private static ThreadLocal<Page> tlPage = new ThreadLocal<>();
    private ThreadLocal<Playwright> tlPlaywright = new ThreadLocal<>();

    // GetMethods
    public Playwright getPlaywright(){
        return tlPlaywright.get();
    }

    public Browser getBrowser(){
        return tlBrowser.get();
    }

    public BrowserContext getBrowserContext(){
        return tlBrowserContext.get();
    }

    public static Page getPage(){
        return tlPage.get();
    }

    //Initialize
    public Page initBrowser(Properties prop) {
        return initBrowser(prop, new BrowserType.LaunchOptions().setHeadless(false));
    }
    public Page initBrowser(Properties prop, BrowserType.LaunchOptions options) {

        String browserName = prop.getProperty("browser").trim();
        System.out.println("Browser name is : " + browserName);
        System.out.println("Page URL is: " + prop.getProperty("url").trim());

        //playwright = Playwright.create();
        tlPlaywright.set(Playwright.create());

        switch(browserName.toLowerCase()) {
            case "chromium":
                //browser = playwright.chromium().launch(options);
                tlBrowser.set(getPlaywright().chromium().launch(options));
                break;
            case "firefox":
                //browser = playwright.firefox().launch(options);
                tlBrowser.set(getPlaywright().firefox().launch(options));
                break;
            case "safari":
                //browser = playwright.webkit().launch(options);
                tlBrowser.set(getPlaywright().webkit().launch(options));
                break;
            case "chrome":
                //browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false));
                tlBrowser.set(getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false)));
                break;
            default:
                System.out.println("Please pass a browser name...");
                break;
        }

        //browserContext = browser.newContext();
        //page = browserContext.newPage();
        //page.navigate(prop.getProperty("url").trim());
        tlBrowserContext.set(getBrowser().newContext());
        tlPage.set(getBrowserContext().newPage());
        getPage().navigate(prop.getProperty("url").trim());

        return getPage();
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

    public static String takeScreenshot() {
        String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
        getPage().screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get(path))
                .setFullPage(true));
        return path;
    }
}
