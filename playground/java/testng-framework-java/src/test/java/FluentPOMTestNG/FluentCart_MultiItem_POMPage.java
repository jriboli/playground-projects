package FluentPOMTestNG;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import com.woocommerce.fluentpages.FluentCartPage;
import com.woocommerce.pages.CheckoutPage;
import com.woocommerce.pages.HomePage;
import com.woocommerce.pages.ShopPage;
import com.woocommerce.util.DebugUtil;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FluentCart_MultiItem_POMPage {

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
        ShopPage sp = hp.clickShop();

        fcp = new FluentCartPage(page);
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

    @AfterMethod
    public void teardown() {
        browser.close();
        playwright.close();
    }
}
