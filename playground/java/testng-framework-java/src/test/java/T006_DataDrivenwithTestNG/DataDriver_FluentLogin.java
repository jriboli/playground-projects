package T006_DataDrivenwithTestNG;

import com.microsoft.playwright.BrowserContext;
import com.woocommerce.fluentpages.FluentLoginPage;
import com.woocommerce.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DataDriver_FluentLogin extends BaseTest {

    @BeforeMethod
    public void setup() {
        BrowserContext context = browser.newContext();
        page = context.newPage();

        page.navigate("http://localhost:9090/");

        HomePage hp = new HomePage(page);
        hp.clickLogin();
        flp = new FluentLoginPage(page);
    }

    @Test(dataProvider = "loginCredentials", dataProviderClass = LoginTestData.class)
    public void shouldErrorLogin(String username, String password, boolean shouldLoginSucceed, String errorMessage) {
        String alertText = flp.setPassword(password)
                .setUsername(username)
                .clickLogin()
                .getAlertText();

        if(shouldLoginSucceed) {
            // Do something
        } else {
            Assert.assertEquals(alertText, errorMessage, "Expected error message did not match.");
        }
    }

    @AfterMethod
    public void testdown() {
        page.clock();
    }
}
