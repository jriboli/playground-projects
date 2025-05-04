package T006_DataDrivenTestNG;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.woocommerce.fluentpages.FluentLoginPage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

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
