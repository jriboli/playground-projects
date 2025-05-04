package T001_AnnotationswithTestNG;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class Test004_First_Automated {
    private Playwright playwright;
    private Browser browser;
    private Page page;

    SoftAssert softAssert = new SoftAssert();

    @BeforeClass
    public void setup() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));
        BrowserContext context = browser.newContext();
        page = context.newPage();
    }

    @Test
    public void verifyTitleTest() {
        page.navigate("http://localhost:9090/");
        assert page.title().equals("BinaryNomadStore") : "Page title is incorrect";
    }

    @Test
    public void verifyLinksTest() {
        page.navigate("http://localhost:9090/");
        Locator links = page.locator("#modal-1").getByRole(AriaRole.LINK);
        int count = links.count();
        //System.out.println("Number of links: " + count);

        for (int i = 0; i < count; i++) {
            String href = links.nth(i).getAttribute("href");
            String text = links.nth(i).innerText();
            //System.out.println("Link " + (i + 1) + ": " + text + " -> " + href);
        }

        Assert.assertEquals(5, count, "Expected 5 links in header");
    }

    @Test
    public void verifyMainBody() {
        page.navigate("http://localhost:9090/");
        Locator links = page.getByRole(AriaRole.MAIN).getByRole(AriaRole.LINK);
        int count = links.count();
        System.out.println("Number of links: " + count);

        for (int i = 0; i < count; i++) {
            String href = links.nth(i).getAttribute("href");
            String text = links.nth(i).innerText();
            System.out.println("Link " + (i + 1) + ": " + text + " -> " + href);
        }

        Locator helloLink = links.getByText("Hello World!");
        System.out.println("Hello: " + helloLink);

        Assert.assertTrue(links.getByText("Hello World!").isVisible());
    }

    @Test
    public void verifyLogin() {
        page.navigate("http://localhost:9090/");
        Locator login = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Login"));

        System.out.println("Visible: " + login.nth(0).isVisible());
        System.out.println("Logins: " + login.count());

        login.click();

        Locator usernameTxt = page.getByLabel("username");
        usernameTxt.fill("rocket");
        //Locator passwordTxt = page.getByLabel("password");
        Locator passwordTxt = page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Password"));
        passwordTxt.fill("l3iBmkszkYuxlA(IfkzUjNO&");
        Locator loginBtn = page.getByText("Log in");
        loginBtn.click();

        Assert.assertEquals(page.url(), "http://localhost:9090/?page_id=9");
    }

    @Test
    public void verifyParameterizedLogin() {
        page.navigate("http://localhost:9090/");
        Locator login = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Login"));
        login.click();

        Locator usernameTxt = page.getByLabel("username");
        usernameTxt.fill("rocket");
        Locator passwordTxt = page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Password"));
        passwordTxt.fill("l3iBmkszkYuxlA(IfkzUjNO&");

        Locator loginBtn = page.getByText("Log in");
        loginBtn.click();

    }

    @Test
    public void verifyEmptyLogin() {
        page.navigate("http://localhost:9090/");
        Locator login = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Login"));
        login.click();

        boolean myAccountVisible = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("My account")).isVisible();
        System.out.println("Is myAccount visible: " + myAccountVisible);

        Locator loginBtn = page.getByText("Log in");
        System.out.println("Login Btn: " + loginBtn.count());
        loginBtn.waitFor();
        loginBtn.click();

        var alertText = page.getByRole(AriaRole.ALERT).textContent();
        Assert.assertEquals(alertText.trim(), "Error: Username is required.");
    }

    @Test
    public void verifyCartLink() {
        page.navigate("http://localhost:9090/");
        Locator cartBtn = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Cart"));
        cartBtn.click();

        Assert.assertEquals(page.url(), "http://localhost:9090/?page_id=7");
    }

    @Test
    public void verifyShopLink() {
        page.navigate("http://localhost:9090/");
        Locator cartBtn = page.locator("#modal-1-content").getByRole(AriaRole.LINK, new Locator.GetByRoleOptions().setName("Shop"));
        cartBtn.click();

        softAssert.assertEquals(page.url(), "http://localhost:9090/?post_type=product", "Incorrect URL.");
        Locator listItems = page.getByRole(AriaRole.LIST).nth(2).getByRole(AriaRole.LISTITEM);
        int count = listItems.count();
        for (int i = 0; i < count; i++) {
            //String href = listItems.nth(i).getAttribute("href");
            String text = listItems.nth(i).innerText();
            //System.out.println("listItems " + (i + 1) + ": " + text);
        }
        softAssert.assertEquals(count, 16, "Expected 16 Product Items.");
        softAssert.assertAll();
    }



    @AfterClass
    public void tearDown() {
        browser.close();
        playwright.close();
    }
}
