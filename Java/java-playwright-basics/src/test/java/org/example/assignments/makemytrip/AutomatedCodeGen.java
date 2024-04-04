package org.example.assignments.makemytrip;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.example.assignments.BaseTest;
import org.testng.annotations.Test;

import java.util.regex.Pattern;

public class AutomatedCodeGen extends BaseTest {

    @Test
    public void searchWithCodeGen() {

        searchPage.page.getByText("FromNYC, All Airports US").click();
        searchPage.page.getByPlaceholder("From").fill("Los Angeles");
        searchPage.page.getByPlaceholder("From").press("Enter");
        searchPage.page.locator("div").filter(new Locator.FilterOptions().setHasText(Pattern.compile("^Los Angeles, United StatesLAX Los Angeles-CA$"))).first().click();
        searchPage.page.getByText("ToDEL, Delhi Airport India").click();
        searchPage.page.getByPlaceholder("To").fill("Seattle");
        searchPage.page.getByPlaceholder("To").press("Enter");
        searchPage.page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName("icon Seattle, United States SEA Seattle/Tacoma Intl-WA")).click();
        searchPage.page.getByLabel("Mon Apr 01").getByText("1").click();
        searchPage.page.getByText("Tap to add a return date for").click();
        searchPage.page.getByLabel("Tue Apr 02").getByText("2").click();
        searchPage.page.getByText("Search", new Page.GetByTextOptions().setExact(true)).click();
    }
}
