package com.woocommerce.pages.cartComponents;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import java.util.regex.Pattern;

public class CartItem {
    private Locator root;

    // Locators
    Locator itemTotal;
    Locator itemName;
    Locator itemDescription;
    Locator itemIncreaseQuantity;
    Locator itemDecreaseQuantity;
    Locator itemQuantity;
    Locator itemRemove;

    public CartItem(Locator root) {
        this.root = root;

        // price wc-block-components-product-price
        itemTotal = root.locator("td[class='wc-block-cart-item__total']");
        itemName = root.getByRole(AriaRole.LINK);
        itemDescription = root.locator("div[class='wc-block-components-products-metadata__description']");
        itemIncreaseQuantity = root.getByRole(AriaRole.BUTTON,
                new Locator.GetByRoleOptions().setName(Pattern.compile("\\bIncrease\\b")));
        itemDecreaseQuantity = root.getByRole(AriaRole.BUTTON,
                new Locator.GetByRoleOptions().setName(Pattern.compile("\\bDecrease\\b")));
        itemQuantity = root.locator("input[type='number']");
        itemRemove = root.getByRole(AriaRole.BUTTON, new Locator.GetByRoleOptions().setName("Remove item"));
    }

    public String getProductName() {
        return itemName.textContent();
    }

    public String getTotalPrice() {
        return itemTotal.innerText();
    }

    public String getProductDescription() {
        return itemDescription.innerText();
    }

    public String getProductQuantity() {
        return itemQuantity.inputValue();
    }

    public void setProductQuantity(int quantity) {
        itemQuantity.fill(quantity + "");
    }

    public void increaseQuantity() {
        itemIncreaseQuantity.click();
    }

    public void decreaseQuantity() {
        itemDecreaseQuantity.click();
    }

    public void removeItem() {
        itemRemove.click();
    }

    public void printDetails() {
        System.out.println("SOME CART ITEM DETAILS --- ");
        System.out.println("Product Name: " + getProductName());
        System.out.println("Product Total: " + getTotalPrice());
        System.out.println("Product Quantity: " + getProductQuantity());
    }
}
