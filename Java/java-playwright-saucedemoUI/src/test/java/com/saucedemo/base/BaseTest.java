package com.saucedemo.base;

import com.microsoft.playwright.Page;
import com.saucedemo.factory.PlaywrightFactory;
import com.saucedemo.pages.InventoryPage;
import com.saucedemo.pages.LoginPage;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.util.Properties;

public class BaseTest {
    PlaywrightFactory pf;
    protected Properties prop;
    Page page;

    // Protected - can be accessed within the same package
    protected LoginPage loginPage;
    protected InventoryPage inventoryPage;
    @BeforeTest
    public void setup() {
        pf = new PlaywrightFactory();
        prop = pf.init_prop();
        page = pf.initBrowser(prop);
        loginPage = new LoginPage(page);
    }

    @AfterTest
    public void tearDown() {
        page.context().browser().close();
    }
}
