package com.saucedemo.tests;

import com.saucedemo.base.BaseTest;
import com.saucedemo.constants.AppConstants;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Properties;

public class InventoryPageTest extends BaseTest {

    @BeforeTest
    public void login() {
        Properties localProperties = this.prop;
        System.out.println("InventoryPageTest prop : " + localProperties.toString());
        inventoryPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
    }

    @Test
    public void inventoryPageTitleTest() {
        String actualTitle = inventoryPage.getInventoryPageTitle();
        Assert.assertEquals(actualTitle, AppConstants.LOGIN_PAGE_TITLE);
    }

    @Test
    public void inventoryPageURLTest() {
        String actualURL = inventoryPage.getInventoryPageURL();
        Assert.assertEquals(actualURL, prop.getProperty("url") + "inventory.html");
    }

    @DataProvider
    public Object[][] getItemData() {
        return new Object[][] {
                {"Sauce Labs Backpack"},
                {"Sauce Labs Bike Light"},
                {"Sauce Labs Bolt T-Shirt"},
                {"Sauce Labs Fleece Jacket"}
        };
    }
    @Test(dataProvider = "getItemData")
    public void investoryItemTest(String itemName) {
        Assert.assertTrue(inventoryPage.isItemExists(itemName));
    }

    @Test
    public void addItemToCartTest() {
        inventoryPage.addItemToCart();
        String count = inventoryPage.getShippingCartCount();
        Assert.assertEquals(count, "1");
    }
}
