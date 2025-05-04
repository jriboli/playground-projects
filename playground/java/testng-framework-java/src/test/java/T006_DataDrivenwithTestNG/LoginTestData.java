package T006_DataDrivenwithTestNG;

import org.testng.annotations.DataProvider;

public class LoginTestData {

    @DataProvider(name = "loginCredentials")
    public static Object[][] credentials() {
        return new Object[][] {
                {null, "abc123", false, "Error: Username is required."},
                {"rocket", null, false, "Error: The password field is empty."},
                {"rocket", "abc123", false, "Error: The password you entered for the username rocket is incorrect. Lost your password?"},
                {"deadpool", "abc123", true, "Error: The username deadpool is not registered on this site. If you are unsure of your username, try your email address instead."},
        };
    }
}
