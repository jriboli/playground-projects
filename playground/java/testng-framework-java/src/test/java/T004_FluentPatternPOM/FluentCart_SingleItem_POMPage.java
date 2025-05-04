package T004_FluentPatternPOM;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import com.woocommerce.fluentpages.FluentCartPage;
import com.woocommerce.pages.CheckoutPage;
import com.woocommerce.pages.HomePage;
import com.woocommerce.util.DebugUtil;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FluentCart_SingleItem_POMPage {
    private Playwright playwright;
    private Browser browser;
    private Page page;

    private FluentCartPage fcp;

    private static final String VALID_COUPON = "fiveoff";
    private static final String INVALID_COUPON = "abc123";

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
    public void shouldDisplayCartHeading() {
        Locator cartHeading = page.getByRole(AriaRole.HEADING);
        DebugUtil.printLocatorInfo(cartHeading, "Cart Heading");

        Assert.assertTrue(cartHeading.isVisible(), "Cart heading is not visible.");
    }

    @Test
    public void shouldAddNewItemToCart() {
        int cartItems = fcp.addItemToCart()
                .numOfItemsInCart();

        Assert.assertEquals(cartItems, 1, "Expected only 1 item in cart.");
    }

    @Test
    public void shouldIncreaseCartItemQuantity() {
        String newItemQuantity = fcp.addItemToCart()
                .getCartItemByIndex(0)
                .increaseQuantity()
                .getProductQuantity();

        Assert.assertEquals(newItemQuantity, "2", "Item Quantity should be 2");
    }

    @Test
    public void shouldRemoveItemFromCart() {
        int cartItems = fcp.addItemToCart()
                .getCartItemByIndex(0)
                .removeItem()
                .goBackToCartPage()
                .numOfItemsInCart();

        Assert.assertEquals(cartItems, 0, "Expected no items in cart.");
    }

    @Test
    public void shouldApplyValidCoupon() {
        fcp.addItemToCart()
                .addCoupon(VALID_COUPON);

        float itemPrice = fcp.getCartItemByIndex(0).getTotalPrice();
        float totalPrice = fcp.getCartTotal();

        Assert.assertTrue(itemPrice > totalPrice);
    }

    @Test
    public void shouldTiggerPopUpWhenCouponApplied() {
        String banner = fcp.addItemToCart()
                .addCoupon(VALID_COUPON)
                .getNoticeBanner();

        Assert.assertEquals(banner, "Coupon code " + VALID_COUPON + " has been applied to your cart.", "Banner text incorrect.");
    }

    @Test
    public void shouldTriggerPopUpWhenCouponRemoved() {
        String banner = fcp.addItemToCart()
                .addCoupon(VALID_COUPON)
                .removeCoupon(VALID_COUPON)
                .getNoticeBanner();

        Assert.assertEquals(banner, "Coupon code " + VALID_COUPON + " has been removed from your cart.", "Banner text incorrect.");
    }

    @Test
    public void shouldRejectInvalidCoupon() {
        fcp.addItemToCart()
                .addCoupon(INVALID_COUPON);

        Assert.assertEquals(fcp.getAlertText(), "Coupon " + INVALID_COUPON + " does not exist!", "Does not match expected.");
    }

    @Test
    public void shouldRejectAddSameCouponTwice() {
        fcp.addItemToCart()
                .addCoupon(VALID_COUPON)
                .addCoupon(VALID_COUPON);

        Assert.assertEquals(fcp.getAlertText(), "Coupon code " + VALID_COUPON + " has already been applied.");
    }

    @Test
    public void shouldClickCheckout() {
        CheckoutPage chp = fcp.addItemToCart()
                .clickCheckout();

        Assert.assertTrue(chp.validatePage());
    }

    @AfterMethod
    public void teardown() {
        browser.close();
        playwright.close();
    }
}
