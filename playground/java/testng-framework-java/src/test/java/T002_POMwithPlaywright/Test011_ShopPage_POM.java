package T002_POMwithPlaywright;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import com.woocommerce.pages.ShopPage;
import com.woocommerce.util.DebugUtil;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.nio.file.Paths;

public class Test011_ShopPage_POM {
    private Playwright playwright;
    private Browser browser;
    private Page page;

    private ShopPage sp;

    @BeforeMethod
    public void startLogin() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));
        BrowserContext context = browser.newContext();
        page = context.newPage();

        page.navigate("http://localhost:9090/?post_type=product");
        sp = new ShopPage(page);
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
