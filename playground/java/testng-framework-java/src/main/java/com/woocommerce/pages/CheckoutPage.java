package com.woocommerce.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class CheckoutPage {

    private Page page;

    // Locators
    Locator noticeBanner;
    Locator emailAddress;

    Locator shippingCountry;
    Locator shippingFirstName;
    Locator shippingLastName;
    Locator shippingAddress;
    Locator buttonAddAddressAddl;
    Locator shippingAddressAddl;
    Locator shippingCity;
    Locator shippingState;
    Locator shippingZipCode;
    Locator shippingPhone;

    Locator placeOrderButton;

    Locator orderSummary;

    // Constructors
    public CheckoutPage(Page page) {
        this.page = page;

        noticeBanner = page.getByRole(AriaRole.BANNER);
        emailAddress = page.locator("#email");

        shippingCountry = page.locator("#shipping-country");
        shippingFirstName = page.locator("#shipping-first_name");
        shippingLastName = page.locator("#shipping-last_name");
        shippingAddress = page.locator("#shipping-address_1");
        shippingAddressAddl = page.locator("#shipping-address_2");
        shippingCity = page.locator("#shipping-city");
        shippingCountry = page.locator("#shipping-country");
        shippingState = page.locator("#shipping-state");
        shippingZipCode = page.locator("#shipping-postcode");
        shippingPhone = page.locator("#shipping-phone");

        placeOrderButton = page.getByRole(AriaRole.BUTTON).filter(new Locator.FilterOptions().setHasText("Place Order"));

        orderSummary = page.getByText("Order summary");
    }

    // Actions
    public boolean validatePage() {
        return orderSummary.isVisible();
    }

    public void printShippingDetails() {

    }

    public void setShippingDetails(String firstName, String lastName, String address, String city, String state, String zipCode) {

    }

    public String getEmailAddress() {
        return emailAddress.inputValue();
    }

    public void setEmailAddress(String email) {
        emailAddress.fill(email);
    }

    public String getShippingCountry() {
        return shippingCountry.inputValue();
    }

    public void setShippingCountry(String country) {
        shippingCountry.selectOption(country);
    }

    public String getShippingFirstName() {
        return shippingFirstName.inputValue();
    }

    public void setShippingFirstName(String fname) {
        shippingFirstName.fill(fname);
    }

    public String getShippingLastName() {
        return shippingLastName.inputValue();
    }

    public void setShippingLastName(String lname) {
        shippingLastName.fill(lname);
    }

    public String getShippingAddress() {
        return shippingAddress.inputValue();
    }

    public void setShippingAddress(String address) {
        shippingAddress.fill(address);
    }

    public String getShippingCity() {
        return shippingCity.inputValue();
    }

    public void setShippingCity(String city) {
        shippingCity.fill(city);
    }

    public String getShippingZipCode() {
        return shippingZipCode.inputValue();
    }

    public void setShippingZipCode(String zipcode) {
        shippingZipCode.fill(zipcode);
    }

    public void clickPlaceOrder() {
        placeOrderButton.click();
    }


}
