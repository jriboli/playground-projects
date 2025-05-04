package T002_POMwithPlaywright;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import com.woocommerce.pages.CartPage;
import com.woocommerce.pages.CheckoutPage;
import com.woocommerce.pages.HomePage;
import com.woocommerce.util.DebugUtil;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.nio.file.Paths;
import java.util.regex.Pattern;

public class Test010_CheckoutPage_POM {
    private Playwright playwright;
    private Browser browser;
    private Page page;

    private CheckoutPage chp;

    @BeforeMethod
    public void setup() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));
        BrowserContext context = browser.newContext();
        page = context.newPage();
        page.navigate("http://localhost:9090/");

        HomePage hp = new HomePage(page);
        CartPage cp = hp.clickCart();
        cp.addItemToCart();
        chp = cp.clickCheckout();
    }

    @Test
    public void isCheckoutPage() {
        Locator cartHeading = page.getByRole(AriaRole.HEADING);
        DebugUtil.printLocatorInfo(cartHeading, "Cart Heading");
    }

    @Test
    public void addEmailAddress() {
        chp.setEmailAddress("rockerraccoon@gmail.com");

        Assert.assertEquals(chp.getEmailAddress(), "rockerraccoon@gmail.com", "Email does not match expected.");
    }

    @Test
    public void addAddress() {
        chp.setShippingAddress("123 Main St");
        chp.setShippingFirstName("Rocket");
        chp.setShippingLastName("Raccoon");
        chp.setShippingCity("Somewhere");
        chp.setShippingZipCode("11111");

        chp.clickPlaceOrder();

        assert true;
    }

    @Test
    public void checkBanner() {
        chp.setShippingAddress("123 Main St");
        chp.setShippingFirstName("Rocket");
        chp.setShippingLastName("Raccoon");
        chp.setShippingCity("Somewhere");
        chp.setShippingZipCode("11111");

        chp.clickPlaceOrder();
        // NOte: Can not cain when the Locator has multiple results.
        //Locator noticeBanner = page.locator("div[class='wc-block-components-notices']");
        //Locator bannerContent = noticeBanner.locator("div[class='wc-block-components-notice-banner__content']");

        Locator noticeBanner = page.locator("div[class='wc-block-components-notices']")
                .filter(new Locator.FilterOptions().setHasText(Pattern.compile("\\bpayment\\b")));
        DebugUtil.printLocatorInfo(noticeBanner, "Notice Banner");

        assert false;
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
