package com.woocommerce.fluentpages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.woocommerce.fluentpages.cartComponents.FluentCartCoupon;
import com.woocommerce.fluentpages.cartComponents.FluentCartItem;
import com.woocommerce.pages.CheckoutPage;
import com.woocommerce.pages.cartComponents.CartCoupon;
import com.woocommerce.pages.cartComponents.CartItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FluentCartPage {
    private Page page;

    // Locators
    Locator cartNoticeBanner;

    Locator sectionNewInStore;
    Locator productTiles;
    Locator firstProductToAdd;
    Locator sectionCartDetails;

    Locator checkoutButton;

    Locator addCouponButton;
    Locator enterCouponTxtBx;
    Locator applyCouponButton;
    Locator couponAlert;
    Locator couponList;
    Locator cartTotal;

    Locator cartItemDetails;

    // Constructors
    public FluentCartPage(Page page) {
        this.page = page;

        cartNoticeBanner = page.locator("div[class='wc-block-components-notice-banner__content']");

        // New In Store Section
        sectionNewInStore = page.locator("div[data-block-name='woocommerce/product-new']");
        productTiles = sectionNewInStore.getByRole(AriaRole.LISTITEM);
        firstProductToAdd = productTiles.getByRole(AriaRole.LINK, new Locator.GetByRoleOptions().setName("Add To Cart")).nth(0);

        checkoutButton = page.getByRole(AriaRole.LINK).filter(new Locator.FilterOptions().setHasText("Proceed to Checkout"));

        // Cart Totals Section
        // TODO: Need to rework sectionCartDetails
        sectionCartDetails = page.locator("div[class='wc-block-components-sidebar']");
        addCouponButton = page.getByText("Add a coupon");
        enterCouponTxtBx = page.getByLabel("Enter code");
        applyCouponButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Apply"));
        couponAlert = page.getByRole(AriaRole.ALERT);
        // Coupons Section
        couponList = page.locator("ul[class='wc-block-components-totals-discount__coupon-list']").getByRole(AriaRole.LISTITEM);
        cartTotal = page.locator("div[class='wc-block-components-totals-item__value']").last();

        // Cart Items Section
        cartItemDetails = page.getByRole(AriaRole.TABLE).locator("tr[class='wc-block-cart-items__row']");

    }

    // Component - CartCoupon
    private List<FluentCartCoupon> getCartCoupons() {
        List<FluentCartCoupon> cartCoupon = new ArrayList<>();
        for(int i = 0; i < couponList.count(); i++) {
            FluentCartCoupon item = new FluentCartCoupon(couponList.nth(i), this);
            cartCoupon.add(item);
        }

        return cartCoupon;
    }

    public FluentCartCoupon getCartCouponByName(String name) {
        return getCartCoupons().stream()
                .filter((FluentCartCoupon item) -> item.getCouponName().equals(name))
                .findFirst().orElse(null);
    }

    public FluentCartCoupon getCartCouponByIndex(int index) {
        return getCartCoupons().get(index);
    }

    // Component - CartItem
    private List<FluentCartItem> getCartItems() {
        List<FluentCartItem> cartItems = new ArrayList<>();
        for(int i = 0; i < cartItemDetails.count(); i++) {
            FluentCartItem item = new FluentCartItem(cartItemDetails.nth(i), this);
            cartItems.add(item);
        }

        return cartItems;
    }

    public FluentCartItem getCartItemByName(String name) {
        return getCartItems().stream()
                .filter((FluentCartItem item) -> item.getProductName().equals(name))
                .findFirst().orElse(null);
    }

    public FluentCartItem getCartItemByIndex(int index) {
        return getCartItems().get(index);
    }

    // Actions
    public FluentCartPage addItemToCart() {
        firstProductToAdd.click();
        return this;
    }

    public FluentCartPage addCoupon(String couponCode) {
        addCouponButton.click();
        enterCouponTxtBx.fill(couponCode);
        applyCouponButton.click();
        return this;
    }

    public FluentCartPage removeCoupon(String couponCode) {
        Locator product = couponList.getByRole(AriaRole.LISTITEM, new Locator.GetByRoleOptions().setName(couponCode));
        product.getByRole(AriaRole.BUTTON).click();
        return this;
    }

    public FluentCartPage emptyCart() {
        for(FluentCartItem item : getCartItems()){
            item.removeItem();
        }
        return this;
    }

    public CheckoutPage clickCheckout() {
        checkoutButton.click();
        return new CheckoutPage(page);
    }

    // Get Values
    public String getNoticeBanner() {
        return cartNoticeBanner.innerText();
    }

    public String getAlertText() {
        return couponAlert.textContent();
    }

    public float getCartTotal() {
        String totalPrice = cartTotal.innerText();
        return Float.parseFloat(totalPrice.replace("$", ""));
    }

    public boolean isCartEmpty() {
        return getCartItems().isEmpty();
    }

    public boolean cartHasItemByName(String name) {
        FluentCartItem itemByName = getCartItemByName(name);
        return Objects.isNull(itemByName);
    }

    public int numOfItemsInCart() {
        return getCartItems().size();
    }

    // Just for Debugging/Fun
    public void printCartDetails() {
        String cartDetails = cartTotal.innerText();
        System.out.println("Cart Total: " + cartDetails);
    }
}
