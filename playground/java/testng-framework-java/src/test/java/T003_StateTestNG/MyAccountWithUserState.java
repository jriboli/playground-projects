package T003_StateTestNG;

import helpers.AuthHelper;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import com.woocommerce.pages.LoginPage;
import com.woocommerce.pages.MyAccountPage;
import com.woocommerce.util.DebugUtil;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class MyAccountWithUserState {
    private Playwright playwright;
    private Browser browser;
    private Page page;

    MyAccountPage mp;

    @BeforeMethod
    public void setup() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));
        BrowserContext context = AuthHelper.createContextWithState(playwright, browser, "rocket-state.json");
        page = context.newPage();

        page.navigate("http://localhost:9090/?page_id=9");
        mp = new MyAccountPage(page);
    }

    @Test
    public void isHomePage() {
        assert mp.validatePage();
    }

    @Test
    public void isHomePage2() {
        assertThat(page).hasURL(Pattern.compile(".*localhost*"));
    }

    @Test
    public void hasFooter() {
        assert mp.isFooterVisible();
    }

    // Transition Method - moves to new page - best to return the new Page and show change
    @Test
    public void testLogoutButton() {
        LoginPage lp = mp.clickLogout();
        assert lp.validatePage();
    }

    @Test
    public void testing() {
        Locator details = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Account details")).first();
        DebugUtil.printLocatorInfo(details, "Account Details");
    }

    @AfterMethod
    public void teardown() {
        browser.close();
        playwright.close();
    }
}
