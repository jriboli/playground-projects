package com.woocommerce.fluentpages.cartComponents;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.AriaRole;
import com.woocommerce.fluentpages.FluentCartPage;

import java.util.regex.Pattern;

public class FluentCartItem {
    private Locator root;
    private FluentCartPage parent;

    // Locators
    Locator itemTotal;
    Locator itemName;
    Locator itemDescription;
    Locator itemIncreaseQuantity;
    Locator itemDecreaseQuantity;
    Locator itemQuantity;
    Locator itemRemove;

    public FluentCartItem(Locator root, FluentCartPage parent) {
        this.root = root;
        this.parent = parent;

        // price wc-block-components-product-price
        itemTotal = root.locator("td[class='wc-block-cart-item__total']");
        itemName = root.getByRole(AriaRole.LINK);
        itemDescription = root.locator("div[class='wc-block-components-products-metadata__description']");
        itemIncreaseQuantity = root.getByRole(AriaRole.BUTTON,
                new Locator.GetByRoleOptions().setName(Pattern.compile("\\bIncrease\\b")));
        itemDecreaseQuantity = root.getByRole(AriaRole.BUTTON,
                new Locator.GetByRoleOptions().setName(Pattern.compile("\\bDecrease\\b")));
        itemQuantity = root.locator("input[type='number']");
        itemRemove = root.getByRole(AriaRole.BUTTON, new Locator.GetByRoleOptions().setName(Pattern.compile("\\bRemove\\b")));
    }

    // Actions
    public FluentCartItem setProductQuantity(int quantity) {
        itemQuantity.fill(quantity + "");
        return this;
    }

    public FluentCartItem increaseQuantity() {
        itemIncreaseQuantity.click();
        return this;
    }

    public FluentCartItem decreaseQuantity() {
        itemDecreaseQuantity.click();
        return this;
    }

    public FluentCartItem removeItem() {
        itemRemove.click();
        return this;
    }

    public FluentCartItem printDetails() {
        System.out.println("SOME CART ITEM DETAILS --- ");
        System.out.println("Product Name: " + getProductName());
        System.out.println("Product Total: " + getTotalPrice());
        System.out.println("Product Quantity: " + getProductQuantity());
        System.out.println("Product Description: " + getProductDescription());
        return this;
    }

    // Get Values
    public String getProductName() {
        return itemName.textContent();
    }

    public float getTotalPrice() {
        return Float.parseFloat(itemTotal.innerText().replace("$", ""));
    }

    public String getProductDescription() {
        return itemDescription.innerText();
    }

    public String getProductQuantity() {
        return itemQuantity.inputValue();
    }

    // Navigation
    public FluentCartPage goBackToCartPage() {
        return parent;
    }

}
