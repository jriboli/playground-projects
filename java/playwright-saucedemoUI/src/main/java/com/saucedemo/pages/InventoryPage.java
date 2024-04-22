package com.saucedemo.pages;

import com.microsoft.playwright.Page;

public class InventoryPage {
    private Page page;

    //1. String locators - OR
    private String productsTitle = "//span[text()='Products']";

    //2. Page constructor
    public InventoryPage(Page page){
        this.page = page;
    }

    //3. Page actions/methods
    public String getInventoryPageTitle(){
        String title = page.title();
        System.out.println("Page Title: " + title);
        return title;
    }

    public String getInventoryPageURL(){
        String url = page.url();
        System.out.println("Page URL: " + url);
        return url;
    }

    public boolean isProductsTitleExists(){
        return page.isVisible(productsTitle);
    }

    public boolean isItemExists(String itemName) {
        return page.isVisible("//div[text()='" + itemName +"']");
    }

    public void addItemToCart() {
        page.click("(//button[contains(@class,'btn btn_primary')])[1]");
    }

    public String getShippingCartCount() {
        String count = page.textContent("//a[@class='shopping_cart_link']//span[1]");
        System.out.println("Current cart count: " + count);
        return count;
    }


}
