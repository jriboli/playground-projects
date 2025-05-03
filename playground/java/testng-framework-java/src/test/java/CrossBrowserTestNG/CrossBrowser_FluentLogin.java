package CrossBrowserTestNG;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CrossBrowser_FluentLogin extends BaseTest {

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
}
