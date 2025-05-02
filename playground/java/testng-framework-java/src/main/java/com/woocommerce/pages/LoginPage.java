package com.woocommerce.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import java.util.regex.Pattern;

public class LoginPage extends BasePage {

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
    public LoginPage(Page page) {
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
        return usernameTxtBx.inputValue();
    }

    public void setPassword(String password) {
        passwordTxtBx.fill(password);
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

    public void clickLogin() {
        loginBtn.click();
    }

    public LostPasswordPage clickLostPassword() {
        lostPasswordBtn.click();
        return new LostPasswordPage(page);
    }

    public String getAlertText() {
        return alert.textContent().trim();
    }

    public void toggleShowPassword() {
        showPasswordToggle.click();
    }


}
