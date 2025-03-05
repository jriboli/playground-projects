package com.saucedemo.base;

import com.microsoft.playwright.Page;
import com.saucedemo.factory.PlaywrightFactory;
import com.saucedemo.pages.InventoryPage;
import com.saucedemo.pages.LoginPage;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.util.Properties;

public class BaseTest {
    public PlaywrightFactory pf;
    Page page;

    // Protected - can be accessed within the same package
    protected LoginPage loginPage;
    protected InventoryPage inventoryPage;
    protected Properties prop;

    @BeforeSuite
    public void setup() {
        pf = new PlaywrightFactory();
        prop = pf.init_prop();
        page = pf.initBrowser(prop);
        loginPage = new LoginPage(page);

        System.out.println("BaseTest prop : " + prop.toString());
    }

    @AfterSuite
    public void tearDown() {
        page.context().browser().close();
    }
}
