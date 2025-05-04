package T002_POMTestNG;

import com.microsoft.playwright.*;
import com.woocommerce.pages.HomePage;
import com.woocommerce.pages.LoginPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class Test007_HomePage_POM {
    private Playwright playwright;
    private Browser browser;
    private Page page;

    HomePage hp;

    @BeforeMethod
    public void setup() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));
        BrowserContext context = browser.newContext();
        page = context.newPage();

        page.navigate("http://localhost:9090/");
        hp = new HomePage(page);
    }

    @Test
    public void isHomePage() {
        assert hp.validatePage();
    }

    @Test
    public void isHomePage2() {
        assertThat(page).hasURL(Pattern.compile(".*localhost*"));
    }

    @Test
    public void hasFooter() {
        assert hp.isFooterVisible();
    }

    // Transition Method - moves to new page - best to return the new Page and show change
    @Test
    public void testLoginButton() {
        LoginPage lp = hp.clickLogin();
        assert lp.validatePage();
    }

    @AfterMethod
    public void teardown() {
        browser.close();
        playwright.close();
    }
}
