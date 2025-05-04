package T002_POMwithPlaywright;

import com.microsoft.playwright.*;
import com.woocommerce.pages.HomePage;
import com.woocommerce.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class Test008b_NegLoginPage_POM {

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
    public void shouldErrorWhenMissingUsername() {
        lp.setPassword("abc123");
        lp.clickLogin();
        String alertText = lp.getAlertText();

        Assert.assertEquals(alertText, "Error: Username is required.", "Username required incorrect message");
    }

    @Test
    public void shouldErrorWhenMissingPassword() {
        lp.setUsername("rocket");
        lp.clickLogin();
        String alertText = lp.getAlertText();

        Assert.assertEquals(alertText, "Error: The password field is empty.", "Password required incorrect message");
    }

    @Test
    public void shouldErrorWhenPasswordIncorrect() {
        lp.setUsername("rocket");
        lp.setPassword("abc123");
        lp.clickLogin();
        String alertText = lp.getAlertText();

        Assert.assertEquals(alertText, "Error: The password you entered for the username rocket is incorrect. Lost your password?", "Message ... ");
    }

    @Test
    public void shouldErrorWhenAccountInvalid() {
        lp.setUsername("deadpool");
        lp.setPassword("abc123");
        lp.clickLogin();
        String alertText = lp.getAlertText();

        Assert.assertEquals(alertText, "Error: The username deadpool is not registered on this site. If you are unsure of your username, try your email address instead.", "Message ... ");
    }

    @AfterMethod
    public void teardown() {
        browser.close();
        playwright.close();
    }
}
