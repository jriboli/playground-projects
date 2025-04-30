package DemoTestNG;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class Test005_Parameterized_By_XML {
    private Playwright playwright;
    private Browser browser;
    private Page page;

    SoftAssert softAssert = new SoftAssert();

    @Parameters({"URL"})
    @BeforeClass
    public void setup(String url) {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));
        BrowserContext context = browser.newContext();
        page = context.newPage();

        page.navigate(url);
    }

    @Test
    @Parameters({"Task", "Username", "Password"})
    public void verifyParameterizedLogin(String task, String username, String password) {
        Locator login = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Login"));
        login.click();

        Locator usernameTxt = page.getByLabel("username");
        usernameTxt.fill(username);
        Locator passwordTxt = page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Password"));
        passwordTxt.fill(password);

        Locator loginBtn = page.getByText("Log in");
        loginBtn.click();
    }

    @AfterClass
    public void tearDown() {
        browser.close();
        playwright.close();
    }
}
