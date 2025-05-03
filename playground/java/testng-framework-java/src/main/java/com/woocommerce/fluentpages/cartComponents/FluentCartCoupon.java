package com.woocommerce.fluentpages.cartComponents;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.AriaRole;
import com.woocommerce.fluentpages.FluentCartPage;

public class FluentCartCoupon {
    private Locator root;
    private FluentCartPage parent;

    // Locators
    Locator removeButton;
    Locator couponName;

    // Constructors
    public FluentCartCoupon(Locator root, FluentCartPage parent) {
        this.root = root;
        this.parent = parent;

        removeButton = root.getByRole(AriaRole.BUTTON);
        couponName = root.locator("span").first();
    }


    // Actions
    public FluentCartCoupon clickRemove() {
        removeButton.click();
        return this;
    }

    // Get Values
    public String getCouponName() {
        return couponName.innerText();
    }

    // Navigation
    public FluentCartPage goBackToCartPage() {
        return parent;
    }
}
