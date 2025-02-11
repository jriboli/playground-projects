package org.example.assignments.makemytrip;

import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.util.List;
import java.util.Objects;

public class SearchPage {

    public Page page;

    public SearchPage(Page page) { this.page = page; }

    public String getSearchFromCity() {

        Locator fromCity = page.locator("//label[@for='fromCity']");
        return fromCity.locator("p").getAttribute("title");
    }

    public String getSearchToCity() {

        Locator fromCity = page.locator("//label[@for='toCity']");
        return fromCity.locator("p").getAttribute("title");
    }

    public void setSearchFromCity(String cityName) {
        Locator fromCityBox = page.locator("//label[@for='fromCity']");
        fromCityBox.click();

        Locator fromCityText = page.getByPlaceholder("From");
        fromCityText.fill(cityName);
        fromCityText.press("Enter");

        // Takes a second to load
        page.waitForTimeout(2000);

        // Had to use the locator first to narrow down the DOM so
        // getByText only returns 1 result
        Locator fromCityDropDownItem = page.locator("li.react-autosuggest__suggestion").getByText(cityName);
        fromCityDropDownItem.click();
    }

    public void setSearchToCity(String cityName) {
        Locator fromCityBox = page.locator("//label[@for='toCity']");
        fromCityBox.click();

        Locator fromCityText = page.getByPlaceholder("To");
        fromCityText.fill(cityName);
        fromCityText.press("Enter");

        // Takes a second to load
        page.waitForTimeout(2000);

        // Had to use the locator first to narrow down the DOM so
        // getByText only returns 1 result
        Locator fromCityDropDownItem = page.locator("li.react-autosuggest__suggestion").getByText(cityName);
        fromCityDropDownItem.click();
    }

    public void getSearchDropDownListFromCity(String cityName) {
        Locator fromCityBox = page.locator("//label[@for='fromCity']");
        fromCityBox.click();

        Locator fromCityText = page.getByPlaceholder("From");
        fromCityText.fill(cityName);
        fromCityText.press("Enter");

        // Takes a second to load
        page.waitForTimeout(2000);

        List< ElementHandle> listItems = page.querySelectorAll("li.react-autosuggest__suggestion");

        for (ElementHandle listItem : listItems) {
            System.out.println(listItem.innerText());
        }
    }

    public String getDepartureDate() {
        return page.locator("input[data-cy='departure']").getAttribute("readonly value");
    }

    public void setDepartureDate() {
        if(isCalendarPresent()) {
            page.press("div.minContainer", "Escape");
        }

        Locator departureBox = page.locator("label[for='departure']");
        departureBox.click();

        // Wait for the date picker to be visible
        page.waitForSelector(".DayPicker-Day");

        // Get the current date
        String today = page.textContent(".DayPicker-Day.DayPicker-Day--today p");

        // Find tomorrow's date and click on it
        ElementHandle tomorrowDateElement = page.querySelector(".DayPicker-Day:not(.DayPicker-Day--disabled):has-text('" + today + "') + .DayPicker-Day");
        tomorrowDateElement.click();

        //page.waitForTimeout(5000);
    }

    public String getReturnDate() {
        return page.locator("input[data-cy='return']").getAttribute("readonly value");
    }

    public void setReturnDate() {
        Locator departureBox = page.locator("div[data-cy='returnArea']");
        departureBox.click();

        // Wait for the date picker to be visible
        page.waitForSelector(".DayPicker-Day");

        // Get the current date
        String today = page.textContent(".DayPicker-Day.DayPicker-Day--today p");

        // Calculate the date one week from now
        int currentDay = Integer.parseInt(today);
        int targetDay = currentDay + 7;
        String targetDate = targetDay + "";

        // Find the element corresponding to the target date and click on it
        ElementHandle targetDateElement = page.querySelector(".DayPicker-Day:not(.DayPicker-Day--disabled):has-text('" + targetDate + "')");
        targetDateElement.click();

        //page.waitForTimeout(5000);
    }

    public String getTravellers() {
        return page.locator("label[for='travellers']").innerText();
    }

    public void setTravellers(int adults, int children, int infants, String travelClass) {
        if(adults > 9) { adults = 1; }
        if(children > 6) { children = 0; }
        if(infants > 6) { infants = 0; }
        if(Objects.isNull(travelClass)) { travelClass = "Economy/Premium Economy"; }

        Locator travellersBox = page.locator("label[for='travellers']");
        travellersBox.click();

        travellerSetHelper("adults", adults+"");
        travellerSetHelper("children", children+"");
        travellerSetHelper("infants", infants+"");
        travellerSetHelper("travelClass", travelClass);

        page.locator("button[data-cy='travellerApplyBtn']").click();

        //page.waitForTimeout(5000);
    }

    public void setFlightType(String type) {
        Locator flightItems = page.locator("ul.fasTabs.latoRegular");

        List<ElementHandle> itemList = flightItems.locator("li").elementHandles();

        for(ElementHandle liElement : itemList) {
            String text = liElement.innerText();
            System.out.println("___" + text + "____" + type + "___");
            if(text.contains(type)) {
                System.out.println("Found match!");
                liElement.click();
                break;
            }
        }
    }

    public ResultsPage doSearch() {
        page.getByText("Search", new Page.GetByTextOptions().setExact(true)).click();

        page.waitForTimeout(5000);

        return new ResultsPage(page);
    }

    // HELPER METHODS ****************************************************
    private void travellerSetHelper(String liSelector, String itemValue) {

        String listItemSelector = "ul.guestCounter li[data-cy^='"+liSelector+"-']";

        Locator dropDown = page.locator("ul.guestCounter").filter(new Locator.FilterOptions().setHas(page.locator("li[data-cy^='"+liSelector+"-']")));
        //System.out.println(dropDown.innerText());

        List<ElementHandle> itemList = dropDown.locator("li").elementHandles();

        for(ElementHandle liElement : itemList) {
            String text = liElement.innerText();
            //System.out.println("___" + text + "____" + itemValue + "___");
            if(text.contains(itemValue)) {
                //System.out.println("Found match!");
                liElement.click();
                break;
            }
        }
    }

    private Boolean isCalendarPresent() {
        return page.locator("div.datePickerContainer").isVisible();
    }

}
