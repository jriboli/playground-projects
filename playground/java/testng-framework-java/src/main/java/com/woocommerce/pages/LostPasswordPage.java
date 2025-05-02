package com.woocommerce.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class LostPasswordPage {

    private Page page;
    // Locators
    Locator heading;
    Locator usernameTxtBx;
    Locator resetPassword;
    Locator noticeBanner;


    // Constructors
    public LostPasswordPage(Page page) {
        this.page = page;

        heading = page.getByRole(AriaRole.HEADING).first();
        usernameTxtBx = page.getByLabel("user_login");
        resetPassword = page.getByRole(AriaRole.BUTTON).filter(new Locator.FilterOptions().setHasText("Reset password"));
        noticeBanner = page.getByRole(AriaRole.BANNER);
    }


    // Actions
    public String getHeading() {
        return heading.innerText();
    }

    public String getUsernameTxtBx() {
        return usernameTxtBx.inputValue();
    }

    public void setUsernameTxtBx(String username) {
        usernameTxtBx.fill(username);
    }

    public void clickResetPassword() {
        resetPassword.click();
    }

    public String getNoticeBanner() {
        return noticeBanner.innerText();
    }
}
