package com.woocommerce.fluentpages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.woocommerce.pages.LostPasswordPage;

import java.util.Objects;
import java.util.regex.Pattern;

public class FluentLoginPage {

    private Page page;

    // Locators
    Locator usernameTxtBx;
    Locator passwordTxtBx;
    Locator loginBtn;
    Locator alert;
    Locator myAccountHeading;
    Locator lostPasswordBtn;
    Locator showPasswordToggle;

    // Constructor
    public FluentLoginPage(Page page) {
        this.page = page;
        usernameTxtBx = page.getByLabel("username");
        passwordTxtBx = page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Password"));
        loginBtn = page.getByText("Log in");
        alert = page.getByRole(AriaRole.ALERT);
        myAccountHeading = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("My account"));
        lostPasswordBtn = page.getByRole(AriaRole.LINK).filter(new Locator.FilterOptions().setHasText("Lost your password?"));
        showPasswordToggle = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(Pattern.compile("Show password|Hide password")));
    }


    // Actions
    public FluentLoginPage loginWithValidAccount() {
        setUsername("rocket");
        setPassword("l3iBmkszkYuxlA(IfkzUjNO&");
        clickLogin();
        return this;
    }

    public FluentLoginPage setUsername(String username) {
        if(Objects.nonNull(username)) {
            usernameTxtBx.fill(username);
        }
        return this;
    }

    public FluentLoginPage setPassword(String password) {
        if(Objects.nonNull(password)) {
            passwordTxtBx.fill(password);
        }
        return this;
    }

    public FluentLoginPage clickLogin() {
        loginBtn.click();
        return this;
    }

    public FluentLoginPage toggleShowPassword() {
        showPasswordToggle.click();
        return this;
    }

    public LostPasswordPage clickLostPassword() {
        lostPasswordBtn.click();
        return new LostPasswordPage(page);
    }

    // Get Values
    public String getUsername() {
        return usernameTxtBx.inputValue();
    }

    public String getPassword() {
        return passwordTxtBx.inputValue();
    }

    public boolean isPasswordVisible() {
        String type = passwordTxtBx.getAttribute("type");
        if (type.equals("password")) {
            return false;
        } else {
            return true;
        }
    }

    public String getAlertText() {
        return alert.textContent().trim();
    }
}
