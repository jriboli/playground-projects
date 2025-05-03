package FluentPOMTestNG;

import com.microsoft.playwright.*;
import com.woocommerce.fluentpages.FluentLoginPage;
import com.woocommerce.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class FluentLogin_POMPage {
    private Playwright playwright;
    private Browser browser;
    private Page page;

    private FluentLoginPage flp;

    @BeforeMethod
    public void setup() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));
        BrowserContext context = browser.newContext();
        page = context.newPage();

        page.navigate("http://localhost:9090/");

        HomePage hp = new HomePage(page);
        hp.clickLogin();
        flp = new FluentLoginPage(page);
    }

    @Test
    public void shouldErrorWhenMissingUsername() {
        String alertText = flp.setPassword("abc123")
                .clickLogin()
                .getAlertText();

        Assert.assertEquals(alertText, "Error: Username is required.", "Username required incorrect message");
    }

    @Test
    public void shouldErrorWhenMissingPassword() {
        String alertText = flp.setUsername("rocket")
                .clickLogin()
                .getAlertText();

        Assert.assertEquals(alertText, "Error: The password field is empty.", "Password required incorrect message");
    }

    @Test
    public void shouldErrorWhenPasswordIncorrect() {
        String alertText = flp.setUsername("rocket")
                .setPassword("abc123")
                .clickLogin()
                .getAlertText();

        Assert.assertEquals(alertText, "Error: The password you entered for the username rocket is incorrect. Lost your password?", "Message ... ");
    }

    @Test
    public void shouldErrorWhenAccountInvalid() {
        String alertText = flp.setUsername("deadpool")
                .setPassword("abc123")
                .clickLogin()
                .getAlertText();

        Assert.assertEquals(alertText, "Error: The username deadpool is not registered on this site. If you are unsure of your username, try your email address instead.", "Message ... ");
    }

    @AfterMethod
    public void teardown() {
        browser.close();
        playwright.close();
    }
}
