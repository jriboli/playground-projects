package CrossBrowserTestNG;

import com.microsoft.playwright.*;
import com.woocommerce.fluentpages.FluentLoginPage;
import com.woocommerce.pages.HomePage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseTest {
    protected Playwright playwright;
    protected Browser browser;
    protected Page page;

    protected FluentLoginPage flp;

    @Parameters("browser")
    @BeforeMethod
    public void setup(@Optional("chromium") String browserType) {
        playwright = Playwright.create();
        BrowserType.LaunchOptions options = new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000);

        browser = switch(browserType.toLowerCase()) {
            case "firefox" -> playwright.firefox().launch(options);
            case "webkit" -> playwright.webkit().launch(options);
            default -> playwright.chromium().launch(options);
        };

        BrowserContext context = browser.newContext();
        page = context.newPage();

        page.navigate("http://localhost:9090/");

        HomePage hp = new HomePage(page);
        hp.clickLogin();
        flp = new FluentLoginPage(page);
    }

    @AfterMethod
    public void teardown() {
        browser.close();
        playwright.close();
    }
}
