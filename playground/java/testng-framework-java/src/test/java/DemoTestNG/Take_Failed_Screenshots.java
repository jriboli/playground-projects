package DemoTestNG;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.nio.file.Paths;

public class Take_Failed_Screenshots {
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
    public void verifyShopLink() {
        Locator cartBtn = page.locator("#modal-1-content").getByRole(AriaRole.LINK, new Locator.GetByRoleOptions().setName("Shop"));
        cartBtn.click();

        softAssert.assertEquals(page.url(), "http://localhost:9090/?post_type=product", "Incorrect URL.");
        Locator listItems = page.getByRole(AriaRole.LIST).nth(2).getByRole(AriaRole.LISTITEM);
        int count = listItems.count();

        softAssert.assertEquals(count, 17, "Expected 16 Product Items.");
        softAssert.assertAll();
    }

    @AfterMethod
    public void takeScreenshotForFailures(ITestResult testResult) {
        if(ITestResult.FAILURE == testResult.getStatus()) {
            page.screenshot(new Page.ScreenshotOptions()
                    .setPath(Paths.get("failure_" + testResult.getName() + ".png"))
                    .setFullPage(true));
        }
    }

    @AfterClass
    public void tearDown() {
        browser.close();
        playwright.close();
    }
}
