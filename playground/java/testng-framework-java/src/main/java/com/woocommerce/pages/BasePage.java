package com.woocommerce.pages;

import com.microsoft.playwright.Playwright;

public class BasePage {

    public static Playwright playwright;

    public void setPlaywright(Playwright playwright) {
        BasePage.playwright = playwright;
    }


}
