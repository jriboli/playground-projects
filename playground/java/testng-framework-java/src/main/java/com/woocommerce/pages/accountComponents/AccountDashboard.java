package com.woocommerce.pages.accountComponents;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class AccountDashboard implements AccountSection{

    private Locator root;

    Locator welcomeText;

    public AccountDashboard(Locator root) {
        this.root = root;

        welcomeText = root.getByRole(AriaRole.PARAGRAPH).first();
    }

    public void assertVisible() {
        root.getByRole(AriaRole.HEADING, new Locator.GetByRoleOptions().setName("Dashboard")).isVisible();
    }

    public String getWelcomeText() {
        return welcomeText.innerText();
    }
}
