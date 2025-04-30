package DemoTestNG;

import com.microsoft.playwright.*;
import com.woocommerce.pages.HomePage;
import com.woocommerce.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class Test008_LoginPage_POM {
    private Playwright playwright;
    private Browser browser;
    private Page page;

    private LoginPage lp;

    @BeforeClass
    public void setup() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));
        BrowserContext context = browser.newContext();
        page = context.newPage();
    }

    @BeforeMethod
    public void startLogin() {
        page.navigate("http://localhost:9090/");

        HomePage hp = new HomePage(page);
        lp = hp.clickLogin();
    }

    @Test
    public void isLoginPage() {
        assert lp.validatePage();
    }

    @Test(enabled = false)
    public void testLogin() {
        lp.loginWithValidAccount();
        assertThat(page).hasTitle("XYZ");
    }

    @Test
    public void testLoginWithoutUsername() {
        lp.setPassword("abc123");
        lp.clickLogin();
        String alertText = lp.getAlertText();
        Assert.assertEquals(alertText, "Error: Username is required.", "Message ... ");
    }

    @Test
    public void testLoginWithoutPassword() {
        lp.setUsername("rocket");
        lp.clickLogin();
        String alertText = lp.getAlertText();
        Assert.assertEquals(alertText, "Error: The password field is empty.", "Message ... ");
    }

    @Test
    public void testLoginWithBadAccount() {
        lp.setUsername("rocket");
        lp.setPassword("abc123");
        lp.clickLogin();
        String alertText = lp.getAlertText();
        Assert.assertEquals(alertText, "Error: The password you entered for the username rocket is incorrect. Lost your password?", "Message ... ");
    }

    @Test
    public void testLostPassword() {

    }

    @AfterClass
    public void teardown() {
        browser.close();
        playwright.close();
    }
}
