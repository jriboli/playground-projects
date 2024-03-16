package com.saucedemo.pages;

import com.microsoft.playwright.Page;

public class LoginPage {

    private Page page;

    //1. String locators - OR
    private String username = "//input[@id='user-name']";
    private String password = "//input[@id='password']";
    private String loginBtn = "//input[@value='Login']";

    //2. Page constructor
    public LoginPage(Page page){
        this.page = page;
    }

    //3. Page actions/methods
    public String getLoginPageTitle(){
        String title = page.title();
        System.out.println("Page Title: " + title);
        return title;
    }

    public String getLoginPageURL(){
        String url = page.url();
        System.out.println("Page URL: " + url);
        return url;
    }

    public boolean isForgotPwdLinkExists() {
        return page.isVisible("forgotPasswordLink");
    }

    // Page chaining model
    public InventoryPage doLogin(String appUserName, String appPassword) {
        System.out.println("App Creds: " + appPassword + ":" + appPassword);
        page.fill(username, appUserName);
        page.fill(password, appPassword);
        page.click(loginBtn);

        return new InventoryPage(page);
    }
}
