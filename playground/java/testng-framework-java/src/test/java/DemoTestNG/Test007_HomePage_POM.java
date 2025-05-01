package DemoTestNG;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import com.woocommerce.pages.HomePage;
import com.woocommerce.util.DebugUtil;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class Test007_HomePage_POM {
    private Playwright playwright;
    private Browser browser;
    private Page page;

    @BeforeMethod
    public void setup() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));
        BrowserContext context = browser.newContext();
        page = context.newPage();

        page.navigate("http://localhost:9090/");
    }

    @Test
    public void isHomePage() {
        HomePage hp = new HomePage(page);
        assert hp.validatePage();
    }

    @Test
    public void isHomePage2() {
        assertThat(page).hasURL(Pattern.compile(".*localhost1abc*"));
    }

    @Test
    public void hasFooter() {
        Locator footer = page.getByRole(AriaRole.CONTENTINFO);
        DebugUtil.printLocatorInfo(footer, "FOOTER");
    }

    // Transition Method - moves to new page - best to return the new Page and show change
    @Test
    public void testLoginButton() {

    }

    @AfterMethod
    public void teardown() {
        browser.close();
        playwright.close();
    }
}
