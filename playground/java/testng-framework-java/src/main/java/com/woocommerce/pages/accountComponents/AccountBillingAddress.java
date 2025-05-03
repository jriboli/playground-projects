package com.woocommerce.pages.accountComponents;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.AriaRole;

public class AccountBillingAddress extends AccountShippingAddress {
    private Locator root;

    Locator firstName;
    Locator lastName;
    Locator country;
    Locator address1;
    Locator address2;
    Locator city;
    Locator state;
    Locator zipcode;

    public AccountBillingAddress(Locator root) {
        super(root);
        this.root = root;


    }

    public void assertVisible() {
        root.getByRole(AriaRole.HEADING, new Locator.GetByRoleOptions().setName("Dashboard")).isVisible();
    }
}
