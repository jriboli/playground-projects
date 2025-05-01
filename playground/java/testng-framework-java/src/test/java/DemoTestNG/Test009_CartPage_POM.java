package DemoTestNG;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import com.woocommerce.pages.CartPage;
import com.woocommerce.pages.HomePage;
import com.woocommerce.pages.cartComponents.CartItem;
import com.woocommerce.util.DebugUtil;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Test009_CartPage_POM {
    private Playwright playwright;
    private Browser browser;
    private Page page;

    private CartPage cp;

    @BeforeClass
    public void setup() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));
        BrowserContext context = browser.newContext();
        page = context.newPage();
    }

    @BeforeMethod
    public void startLogin() {
        page.navigate("http://localhost:9090/");

        HomePage hp = new HomePage(page);
        cp = hp.clickCart();
    }

    @Test
    public void isCartPage() {
        Locator cartHeading = page.getByRole(AriaRole.HEADING);
        DebugUtil.printLocatorInfo(cartHeading, "Cart Heading");
    }

    @Test
    public void addNewItemToCart() {
        cp.addItemToCart();
    }

    @Test
    public void checkItemInCart() {
        cp.addItemToCart();

//        Locator cartItemDetails = page.getByRole(AriaRole.TABLE).locator("tr[class='wc-block-cart-items__row']");
//        DebugUtil.printLocatorInfo(cartItemDetails, "Cart Items");
//
//        List<CartItem> cartItems = new ArrayList<>();
//        for(int i = 0; i < cartItemDetails.count(); i++) {
//            CartItem item = new CartItem(cartItemDetails.nth(i));
//            cartItems.add(item);
//        }
//
//        cartItems.get(0).printDetails();
        int cartItems = cp.numOfItemsInCart();
        Assert.assertEquals(cartItems, 1, "Expected only 1 item in cart.");
    }

    @Test
    public void increaseCartItemQuantity() {
        cp.addItemToCart();

//        Locator cartItemDetails = page.getByRole(AriaRole.TABLE).locator("tr[class='wc-block-cart-items__row']");
//
//        Locator itemIncreaseQuantity = cartItemDetails.nth(0).getByRole(AriaRole.BUTTON,
//                new Locator.GetByRoleOptions().setName(Pattern.compile("\\bIncrease\\b")));
//        //DebugUtil.printLocatorInfo(itemIncreaseQuantity, "Item Increase");
//
//        Locator itemQuantity = cartItemDetails.nth(0).locator("input[type='number']");
//        DebugUtil.printLocatorInfo(itemQuantity, "Item Quantity");
//
//        System.out.println("Quantity: " + itemQuantity.inputValue());

        cp.increaseItemQuantityByIndex(0);
        String newItemQuantity = cp.getItemQuantityByIndex(0);
        Assert.assertEquals(newItemQuantity, 2, "Item Quantity should be 2");
    }

    @Test
    public void removeItemFromCart() {
        cp.addItemToCart();

        cp.removeItemByIndex(0);
        int cartItems = cp.numOfItemsInCart();
        Assert.assertEquals(cartItems, 0, "Expected no items in cart.");
    }
    
    @Test
    public void addCoupon() {
        cp.addItemToCart();
        cp.addCoupon("fiveoff");


    }

    @Test
    public void addInvalidCoupon() {
        cp.addItemToCart();
        cp.addCoupon("abc123");

        Assert.assertEquals(cp.getAlertText(), "Coupon \"abc123\" does not exist!", "Does not match expected.");
    }

    @AfterClass
    public void teardown() {
        browser.close();
        playwright.close();
    }

}
