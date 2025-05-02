package com.woocommerce.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.woocommerce.pages.cartComponents.CartCoupon;
import com.woocommerce.pages.cartComponents.CartItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CartPage {
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
    public CartPage(Page page) {
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

    // Actions
    public String getNoticeBanner() {
        return cartNoticeBanner.innerText();
    }

    public void addItemToCart() {
        firstProductToAdd.click();
    }

    public void addCoupon(String couponCode) {
        addCouponButton.click();
        enterCouponTxtBx.fill(couponCode);
        applyCouponButton.click();
    }

    public void removeCoupon(String couponCode) {
        Locator product = couponList.getByRole(AriaRole.LISTITEM, new Locator.GetByRoleOptions().setName(couponCode));
        product.getByRole(AriaRole.BUTTON).click();
    }

    public String getAlertText() {
        return couponAlert.textContent();
    }

    public CheckoutPage clickCheckout() {
        checkoutButton.click();
        return new CheckoutPage(page);
    }

    public float getCartTotal() {
        String totalPrice = cartTotal.innerText();
        return Float.parseFloat(totalPrice.replace("$", ""));
    }

    // Just for Debugging/Fun
    public void printCartDetails() {
        String cartDetails = cartTotal.innerText();
        System.out.println("Cart Total: " + cartDetails);
    }

    // Component - CartCoupon
    private List<CartCoupon> getCartCoupons() {
        List<CartCoupon> cartCoupon = new ArrayList<>();
        for(int i = 0; i < couponList.count(); i++) {
            CartCoupon item = new CartCoupon(couponList.nth(i));
            cartCoupon.add(item);
        }

        return cartCoupon;
    }

    private CartCoupon getCartCouponByName(String name) {
        return getCartCoupons().stream()
                .filter((CartCoupon item) -> item.getCouponName().equals(name))
                .findFirst().orElse(null);
    }

    public void removeCartCouponByIndex(int index) {
        getCartCoupons().get(index).clickRemove();
    }

    public void removeCartCouponByName(String name) {
        getCartCouponByName(name).clickRemove();
    }

    // Component - CartItem
    // Have List<CartItem> dynamic - since it can change throughout a test
    private List<CartItem> getCartItems() {
        List<CartItem> cartItems = new ArrayList<>();
        for(int i = 0; i < cartItemDetails.count(); i++) {
            CartItem item = new CartItem(cartItemDetails.nth(i));
            cartItems.add(item);
        }

        return cartItems;
    }

    // Component Actions
    private CartItem getCartItemByName(String name) {
        return getCartItems().stream()
                .filter((CartItem item) -> item.getProductName().equals(name))
                .findFirst().orElse(null);
    }

    public boolean isCartEmpty() {
        return getCartItems().isEmpty();
    }

    public boolean cartHasItemByName(String name) {
        CartItem itemByName = getCartItemByName(name);
        return Objects.isNull(itemByName);
    }

    public int numOfItemsInCart() {
        return getCartItems().size();
    }

    public void removeItemByIndex(int index) {
        // for first item -> [remove item]
        getCartItems().get(index).removeItem();
    }

    public void removeItemByName(String name) {
        CartItem itemByName = getCartItemByName(name);
        itemByName.removeItem();
    }

    public void emptyCart() {
        for(CartItem item : getCartItems()){
            item.removeItem();
        }
    }

    public String getItemQuantityByIndex(int index) {
        return getCartItems().get(index).getProductQuantity();
    }

    public void setItemQuantityByIndex(int index, int quantity) {
        getCartItems().get(index).setProductQuantity(quantity);
    }

    public void increaseItemQuantityByIndex(int index) {
        getCartItems().get(index).increaseQuantity();
    }

    public void increaseItemQuantityByName(String name) {
        CartItem itemByName = getCartItemByName(name);
        itemByName.increaseQuantity();
    }

    public void decreaseItemQuantityByIndex(int index) {
        getCartItems().get(index).decreaseQuantity();
    }

    public void decreaseItemQuantityByName(String name) {
        // TODO
    }

    public void overrideItemCount(int productCount) {
        // TODO
    }

    public float getItemPriceByIndex(int index) {
        String itemPrice = getCartItems().get(0).getTotalPrice();
        return Float.parseFloat(itemPrice.replace("$", ""));
    }

    // These are just for debugging/fun
    public void printItemDetailsByIndex(int index) {
        getCartItems().get(index).printDetails();
    }

    public void printItemDetailsByName(String name) {
        CartItem itemByName = getCartItemByName(name);
        itemByName.printDetails();
    }
}
