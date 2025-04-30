package com.woocommerce.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class LoginPage extends BasePage {

    private Page page;

    // Locators
    Locator usernameTxtBx;
    Locator passwordTxtBx;
    Locator loginBtn;
    Locator alert;
    Locator myAccountHeading;

    // Constructor
    public LoginPage(Page page) {
        this.page = page;
        usernameTxtBx = page.getByLabel("username");
        passwordTxtBx = page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Password"));
        loginBtn = page.getByText("Log in");
        alert = page.getByRole(AriaRole.ALERT);
        myAccountHeading = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("My account"));
    }


    // Actions
    public boolean validatePage() {
        return myAccountHeading.isVisible();
    }

    public void loginWithValidAccount() {
        setUsername("rocket");
        setPassword("l3iBmkszkYuxlA(IfkzUjNO&");
        clickLogin();
    }

    public void setUsername(String username) {
        usernameTxtBx.fill(username);
    }

    public String getUsername() {
        return usernameTxtBx.innerText();
    }

    public void setPassword(String password) {
        passwordTxtBx.fill(password);
    }

    public String getPassword() {
        return passwordTxtBx.innerText();
    }

    public void clickLogin() {
        loginBtn.click();
    }

    public String getAlertText() {
        return alert.textContent().trim();
    }
}
