package com.woocommerce.pages.accountComponents;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class AccountSectionFactory {

    public static AccountSection getSection(Page page, String sectionName) {
        Locator base = page.locator("div[class='woocommerce-MyAccount-content']");
        switch(sectionName.toLowerCase()) {
            case "dashboard": return new AccountDashboard(base);
            default: throw new IllegalArgumentException("Unknown section: " + sectionName);
        }
    }
}
