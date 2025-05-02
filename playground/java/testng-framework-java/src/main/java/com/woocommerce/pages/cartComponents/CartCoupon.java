package com.woocommerce.pages.cartComponents;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.AriaRole;

public class CartCoupon {

    private Locator root;

    // Locators
    Locator removeButton;
    Locator couponName;

    // Constructors
    public CartCoupon(Locator root) {
        this.root = root;
        removeButton = root.getByRole(AriaRole.BUTTON);
        couponName = root.locator("span").first();
    }


    // Actions
    public void clickRemove() {
        removeButton.click();
    }

    public String getCouponName() {
        return couponName.innerText();
    }
}
