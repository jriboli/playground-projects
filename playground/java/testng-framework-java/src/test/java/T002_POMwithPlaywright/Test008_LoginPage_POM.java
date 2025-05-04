package T002_POMwithPlaywright;

import com.microsoft.playwright.*;
import com.woocommerce.pages.HomePage;
import com.woocommerce.pages.LoginPage;
import com.woocommerce.pages.LostPasswordPage;
import org.testng.Assert;
import org.testng.annotations.*;


import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class Test008_LoginPage_POM {
    private Playwright playwright;
    private Browser browser;
    private Page page;

    private LoginPage lp;

    @BeforeMethod
    public void startLogin() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));
        BrowserContext context = browser.newContext();
        page = context.newPage();

        page.navigate("http://localhost:9090/");

        HomePage hp = new HomePage(page);
        lp = hp.clickLogin();
    }

    @Test
    public void isLoginPage() {
        assert lp.validatePage();
    }

    @Test()
    public void testLogin() {
        lp.loginWithValidAccount();

        assertThat(page).hasTitle("My account – BinaryNomadStore");
    }

    @Test
    public void testLostPassword() {
        LostPasswordPage lpp = lp.clickLostPassword();

        Assert.assertEquals(lpp.getHeading(), "Lost password", "Lost Password Page heading is incorrect.");
    }

    @Test
    public void shouldShowPasswordWhenToggled() {
        lp.setPassword("password@1");
        lp.toggleShowPassword();

        assert lp.isPasswordVisible();
    }

    @AfterMethod
    public void teardown() {
        browser.close();
        playwright.close();
    }
}
