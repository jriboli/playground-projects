package com.woocommerce.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class HomePage extends BasePage{

    private Page page;

    // Locators
    Locator header;
    Locator loginButton;
    Locator cartButton;

    // Constructor
    public HomePage(Page page) {
        this.page = page;
        header = page.getByRole(AriaRole.BANNER).getByRole(AriaRole.PARAGRAPH);
        loginButton = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Login"));
        cartButton = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Cart"));
    }

    // Actions
    public boolean validatePage() {
        return header.innerText().equals("BinaryNomadStore") &&
                page.url().equals("http://localhost:9090/");
    }

    public LoginPage clickLogin() {
        loginButton.click();
        return new LoginPage(page);
    }

    public void clickCart() {
        cartButton.click();
    }
}
