package org.example.assignments;

import com.microsoft.playwright.Page;
import org.example.assignments.factory.PlaywrightFactory;
import org.example.assignments.makemytrip.SearchPage;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.util.Properties;

public class MakeMyTripBaseTest {
    public PlaywrightFactory pf;
    Page page;

    // Protected - can be accessed within the same package
    protected SearchPage searchPage;
    protected Properties prop;

    @BeforeSuite
    public void setup() {
        pf = new PlaywrightFactory();
        prop = pf.init_prop("./src/test/resources/config/config.properties");
        page = pf.initBrowser(prop);
        searchPage = new SearchPage(page);

        System.out.println("BaseTest prop : " + prop.toString());
    }

    @AfterSuite
    public void tearDown() {
        page.context().browser().close();
    }
}
