package com.woocommerce.pages.accountComponents;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.AriaRole;

public class AccountAddresses implements AccountSection{

    private Locator root;

    public AccountAddresses(Locator root) {
        this.root = root;
    }

    public void assertVisible() {
        root.getByRole(AriaRole.HEADING, new Locator.GetByRoleOptions().setName("Dashboard")).isVisible();
    }
}
