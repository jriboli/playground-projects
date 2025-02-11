package org.example.assignments;

import com.microsoft.playwright.Page;
import org.example.assignments.factory.PlaywrightFactory;
import org.example.assignments.tizag.HomePage;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.util.Properties;

public class TizagBaseTest {
    public PlaywrightFactory pf;
    Page page;

    // Protected - can be accessed within the same package
    protected HomePage homePage;
    protected Properties prop;

    @BeforeSuite
    public void setup() {
        pf = new PlaywrightFactory();
        prop = pf.init_prop("./src/test/resources/config/tizagConfig.properties");
        page = pf.initBrowser(prop);
        homePage = new HomePage(page);

        System.out.println("BaseTest prop : " + prop.toString());
    }

    @AfterSuite
    public void tearDown() {
        page.context().browser().close();
    }
}
