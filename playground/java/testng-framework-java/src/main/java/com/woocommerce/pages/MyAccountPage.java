package com.woocommerce.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.woocommerce.pages.accountComponents.AccountSection;
import com.woocommerce.pages.accountComponents.AccountSectionFactory;

public class MyAccountPage {
    private Page page;

    // Locators
    Locator header;
    Locator dashboardButton;
    Locator ordersButton;
    Locator downloadsButton;
    Locator addressesButton;
    Locator accountDetailsButton;
    Locator logoutButton;
    Locator footer;

    AccountSection section;
    // Constructor
    public MyAccountPage(Page page) {
        this.page = page;
        header = page.getByRole(AriaRole.BANNER).getByRole(AriaRole.PARAGRAPH);
        logoutButton = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Log out"));
        dashboardButton = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Account details")).first();

        footer = page.getByRole(AriaRole.CONTENTINFO);
    }

    // Actions
    public boolean validatePage() {
        return header.innerText().equals("BinaryNomadStore") &&
                page.url().equals("http://localhost:9090/?page_id=9");
    }

    public LoginPage clickLogout() {
        logoutButton.click();
        return new LoginPage(page);
    }

    public boolean isFooterVisible() {
        return footer.isVisible();
    }

    // Dashboard
    public void clickDashboard() {
        dashboardButton.click();
        section = AccountSectionFactory.getSection(page, "dashboard");
    }

    // Orders
    public void clickOrders() {
        dashboardButton.click();
        section = AccountSectionFactory.getSection(page, "orders");
    }

    // Downloads
    public void clickDownloads() {
        dashboardButton.click();
        section = AccountSectionFactory.getSection(page, "downloads");
    }

    // Addresses
    public void clickAddresses() {
        dashboardButton.click();
        section = AccountSectionFactory.getSection(page, "addresses");
    }

    // Account Details
    public void clickAccountDetails() {
        dashboardButton.click();
        section = AccountSectionFactory.getSection(page, "account details");
    }

}
