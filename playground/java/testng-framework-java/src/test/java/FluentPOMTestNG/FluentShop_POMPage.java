package FluentPOMTestNG;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import com.woocommerce.fluentpages.FluentShopPage;
import com.woocommerce.util.DebugUtil;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.nio.file.Paths;

public class FluentShop_POMPage {
    private Playwright playwright;
    private Browser browser;
    private Page page;

    private FluentShopPage fsp;

    @BeforeMethod
    public void setup() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));
        BrowserContext context = browser.newContext();
        page = context.newPage();

        page.navigate("http://localhost:9090/?post_type=product");
        fsp = new FluentShopPage(page);
    }

    @Test
    public void isShopPage() {
        Locator cartHeading = page.getByRole(AriaRole.HEADING);
        DebugUtil.printLocatorInfo(cartHeading, "Cart Heading");
    }

    @Test
    public void checkShopItems() {
        Locator productList = page.locator("ul.wc-block-product-template");
        Locator products = productList.getByRole(AriaRole.LISTITEM);
        DebugUtil.printLocatorInfo(products, "Products");
    }

    @Test
    public void testing() {

    }

    @AfterMethod
    public void takeScreenshotForFailures(ITestResult testResult) {
        if(ITestResult.FAILURE == testResult.getStatus()) {
            page.screenshot(new Page.ScreenshotOptions()
                    .setPath(Paths.get("./resources/screenshots/failure_" + testResult.getName() + ".png"))
                    .setFullPage(true));
        }

        browser.close();
        playwright.close();
    }
}
