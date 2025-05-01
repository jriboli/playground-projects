package com.woocommerce.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.woocommerce.pages.cartComponents.CartItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CartPage {
    private Page page;

    // Locators
    Locator sectionNewInStore;
    Locator productTiles;
    Locator firstProductToAdd;
    Locator sectionCartDetails;

    Locator addCouponButton;
    Locator enterCouponTxtBx;
    Locator applyCouponButton;
    Locator couponAlert;
    Locator cartTotal;

    Locator cartItemDetails;

    // Constructors
    public CartPage(Page page) {
        this.page = page;

        // New In Store Section
        sectionNewInStore = page.locator("div[data-block-name='woocommerce/product-new']");
        productTiles = sectionNewInStore.getByRole(AriaRole.LISTITEM);
        firstProductToAdd = productTiles.getByRole(AriaRole.LINK, new Locator.GetByRoleOptions().setName("Add To Cart")).nth(0);

        // Cart Totals Section
        sectionCartDetails = page.locator("div[class='wc-block-components-sidebar']");
        addCouponButton = page.getByText("Add a coupon");
        enterCouponTxtBx = page.getByLabel("Enter code");
        applyCouponButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Apply"));
        couponAlert = page.getByRole(AriaRole.ALERT);
        cartTotal = sectionCartDetails.locator("div[class='wc-blcok-components-totals-item__value']");

        // Cart Items Section
        cartItemDetails = page.getByRole(AriaRole.TABLE).locator("tr[class='wc-block-cart-items__row']");

    }

    // Actions
    public void addItemToCart() {
        firstProductToAdd.click();
    }

    public void printCartDetails() {
        String cartDetails = cartTotal.innerText();
        System.out.println("Cart Total: " + cartDetails);
    }

    public void addCoupon(String couponCode) {
        addCouponButton.click();
        enterCouponTxtBx.fill(couponCode);
        applyCouponButton.click();
    }

    public String getAlertText() {
        return couponAlert.textContent();
    }

    // Component - CartItem
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

    // These are just for debugging/fun
    public void printItemDetailsByIndex(int index) {
        getCartItems().get(index).printDetails();
    }

    public void printItemDetailsByName(String name) {
        CartItem itemByName = getCartItemByName(name);
        itemByName.printDetails();
    }
}
