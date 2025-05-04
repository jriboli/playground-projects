package T002_POMTestNG;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import com.woocommerce.pages.CartPage;
import com.woocommerce.pages.CheckoutPage;
import com.woocommerce.pages.HomePage;
import com.woocommerce.util.DebugUtil;
import org.testng.Assert;
import org.testng.annotations.*;


public class Test009_CartPage_POM {
    private Playwright playwright;
    private Browser browser;
    private Page page;

    private CartPage cp;

    @BeforeMethod
    public void startLogin() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));
        BrowserContext context = browser.newContext();
        page = context.newPage();
        page.navigate("http://localhost:9090/");

        HomePage hp = new HomePage(page);
        cp = hp.clickCart();
    }

    @Test
    public void shouldDisplayCartHeading() {
        Locator cartHeading = page.getByRole(AriaRole.HEADING);
        DebugUtil.printLocatorInfo(cartHeading, "Cart Heading");

        Assert.assertTrue(cartHeading.isVisible(), "Cart heading is not visible.");
    }

    @Test
    public void shouldAddNewItemToCart() {
        cp.addItemToCart();
        int cartItems = cp.numOfItemsInCart();

        Assert.assertEquals(cartItems, 1, "Expected only 1 item in cart.");
    }

    @Test
    public void shouldIncreaseCartItemQuantity() {
        cp.addItemToCart();
        cp.increaseItemQuantityByIndex(0);
        String newItemQuantity = cp.getItemQuantityByIndex(0);

        Assert.assertEquals(newItemQuantity, "2", "Item Quantity should be 2");
    }

    @Test
    public void shouldRemoveItemFromCart() {
        cp.addItemToCart();
        cp.removeItemByIndex(0);
        int cartItems = cp.numOfItemsInCart();

        Assert.assertEquals(cartItems, 0, "Expected no items in cart.");
    }

    @Test
    public void shouldApplyValidCoupon() {
        cp.addItemToCart();
        cp.addCoupon("fiveoff");

        float itemPrice = cp.getItemPriceByIndex(0);
        float totalPrice = cp.getCartTotal();

        Assert.assertTrue(itemPrice > totalPrice);
    }

    @Test
    public void shouldTiggerPopUpWhenCouponApplied() {
        cp.addItemToCart();
        cp.addCoupon("fiveoff");

        String banner = cp.getNoticeBanner();
        Assert.assertEquals(banner, "Coupon code \"fiveoff\" has been applied to your cart.", "Banner text incorrect.");
    }

    @Test
    public void shouldTriggerPopUpWhenCouponRemoved() {
        cp.addItemToCart();
        cp.addCoupon("fiveoff");
        cp.removeCartCouponByName("fiveoff");

        String banner = cp.getNoticeBanner();
        Assert.assertEquals(banner, "Coupon code \"fiveoff\" has been removed from your cart.", "Banner text incorrect.");
    }

    @Test
    public void shouldRejectInvalidCoupon() {
        cp.addItemToCart();
        cp.addCoupon("abc123");

        Assert.assertEquals(cp.getAlertText(), "Coupon \"abc123\" does not exist!", "Does not match expected.");
    }

    @Test
    public void shouldRejectAddSameCouponTwice() {
        cp.addItemToCart();
        cp.addCoupon("fiveoff");
        cp.addCoupon("fiveoff");

        Assert.assertEquals(cp.getAlertText(), "Coupon code \"fiveoff\" has already been applied.");
    }

    @Test
    public void shouldClickCheckout() {
        cp.addItemToCart();
        cp.clickCheckout();

        CheckoutPage chp = new CheckoutPage(page);
        Assert.assertTrue(chp.validatePage());
    }

    @AfterMethod
    public void teardown() {
        browser.close();
        playwright.close();
    }

}
