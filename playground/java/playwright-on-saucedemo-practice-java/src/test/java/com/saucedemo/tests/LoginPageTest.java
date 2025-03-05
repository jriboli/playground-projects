package com.saucedemo.tests;

import com.saucedemo.base.BaseTest;
import com.saucedemo.constants.AppConstants;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginPageTest extends BaseTest {

    @Test
    public void loginPageTitleTest() {
        String actualTitle = loginPage.getLoginPageTitle();
        Assert.assertEquals(actualTitle, AppConstants.LOGIN_PAGE_TITLE);
    }

    @Test
    public void loginPageURLTest() {
        String actualURL = loginPage.getLoginPageURL();
        Assert.assertEquals(actualURL, prop.getProperty("url"));
    }

    @Test
    public void loginTest(){
        inventoryPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
        Assert.assertTrue(inventoryPage.isProductsTitleExists());
    }

    @Test
    public void invalidLoginTest() {
        inventoryPage = loginPage.doLogin("Rocket", prop.getProperty("password"));
        Assert.assertTrue(inventoryPage.isProductsTitleExists());
    }
}
