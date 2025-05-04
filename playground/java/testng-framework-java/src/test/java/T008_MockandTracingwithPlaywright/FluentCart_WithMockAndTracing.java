package T008_MockandTracingwithPlaywright;

import com.microsoft.playwright.*;
import com.woocommerce.fluentpages.FluentCartPage;
import com.woocommerce.pages.HomePage;
import helpers.MockHelper;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.nio.file.Paths;

public class FluentCart_WithMockAndTracing {

    private Playwright playwright;
    private Browser browser;
    private BrowserContext context;
    private Page page;

    private FluentCartPage fcp;

    private static final String COUPON_CODE = "fiveoff";

    @BeforeMethod
    public void setup() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));
        context = browser.newContext();
        context.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(true));
        page = context.newPage();

        page.navigate("http://localhost:9090/");

        HomePage hp = new HomePage(page);
        hp.clickCart();
        fcp = new FluentCartPage(page);
    }

    @Test
    public void shouldErrorWithMockNetworkErrorAddCoupon() {
        MockHelper.failAddCoupon(page);
        fcp.addItemToCart()
                .addCoupon(COUPON_CODE);

        Assert.assertEquals(fcp.getAlertText(), "You are probably offline", "Does not match expected.");
    }

    @Test
    public void shouldErrorWithMockInvalidCoupon() {
        MockHelper.mockInvalidCouponCode(page);
        fcp.addItemToCart()
                .addCoupon(COUPON_CODE);

        Assert.assertEquals(fcp.getAlertText(), "Coupon \"" + COUPON_CODE + "\" does not exist!", "Does not match expected.");
    }

    @AfterMethod
    public void teardown() {
        context.tracing().stop(new Tracing.StopOptions()
                .setPath(Paths.get("resources/traces/mockcart-trace.zip")));
        browser.close();
        playwright.close();
    }
}
