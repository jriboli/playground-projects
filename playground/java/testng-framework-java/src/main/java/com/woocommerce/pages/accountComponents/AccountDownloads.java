package com.woocommerce.pages.accountComponents;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.AriaRole;

public class AccountDownloads implements AccountSection{

    private Locator root;

    Locator noticeBanner;

    public AccountDownloads(Locator root) {
        this.root = root;

        noticeBanner = root.getByRole(AriaRole.BANNER).first();
    }

    public void assertVisible() {
        root.getByRole(AriaRole.HEADING, new Locator.GetByRoleOptions().setName("Dashboard")).isVisible();
    }

    public String getBannerText() {
        return noticeBanner.innerText();
    }
}
