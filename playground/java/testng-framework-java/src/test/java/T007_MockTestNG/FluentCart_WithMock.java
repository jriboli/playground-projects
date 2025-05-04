package T007_MockTestNG;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import com.woocommerce.fluentpages.FluentCartPage;
import com.woocommerce.pages.HomePage;
import helpers.MockHelper;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FluentCart_WithMock {

    private Playwright playwright;
    private Browser browser;
    private Page page;

    private FluentCartPage fcp;

    private static final String COUPON_CODE = "fiveoff";

    @BeforeMethod
    public void setup() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));
        BrowserContext context = browser.newContext();
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
    }

    @Test
    public void shouldErrorWithMockInvalidCoupon() {
        MockHelper.mockInvalidCouponCode(page);
        fcp.addItemToCart()
                .addCoupon(COUPON_CODE);
    }

    @AfterMethod
    public void teardown() {
        browser.close();
        playwright.close();
    }
}
