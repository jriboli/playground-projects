package com.woocommerce.pages.accountComponents;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.AriaRole;

public class AccountShippingAddress {
    private Locator root;

    public AccountShippingAddress(Locator root) {
        this.root = root;
    }

    public void assertVisible() {
        root.getByRole(AriaRole.HEADING, new Locator.GetByRoleOptions().setName("Dashboard")).isVisible();
    }
}
