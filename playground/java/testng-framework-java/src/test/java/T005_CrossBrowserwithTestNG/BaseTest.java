package T005_CrossBrowserwithTestNG;

import com.microsoft.playwright.*;
import com.woocommerce.fluentpages.FluentLoginPage;
import org.testng.annotations.*;

public class BaseTest {
    protected Playwright playwright;
    protected Browser browser;
    protected Page page;

    protected FluentLoginPage flp;

    @Parameters("browser")
    @BeforeClass
    public void setup(@Optional("chromium") String browserType) {
        playwright = Playwright.create();
        BrowserType.LaunchOptions options = new BrowserType.LaunchOptions().setHeadless(true).setSlowMo(0);

        browser = switch(browserType.toLowerCase()) {
            case "firefox" -> playwright.firefox().launch(options);
            case "webkit" -> playwright.webkit().launch(options);
            default -> playwright.chromium().launch(options);
        };
    }

    @AfterClass
    public void teardown() {
        browser.close();
        playwright.close();
    }
}
