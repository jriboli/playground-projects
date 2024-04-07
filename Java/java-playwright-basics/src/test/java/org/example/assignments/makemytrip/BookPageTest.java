package org.example.assignments.makemytrip;

import org.example.assignments.BaseTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class BookPageTest extends BaseTest {

    private ResultsPage resultsPage;
    private BookPage bookPage;

    @BeforeTest
    public void bookFlight() {
        searchPage.setSearchFromCity("LAX");
        searchPage.setSearchToCity("NYC");
        // Selecting ToCity automatically kicks you into Date Selection
        searchPage.setDepartureDate();
        searchPage.setReturnDate();
        searchPage.setTravellers(3,3,3, "Business");

        resultsPage = searchPage.doSearch();
        bookPage = resultsPage.bookFlightAtIndex(0);
    }

    @Test
    public void checkTitleTest() {
        String title = bookPage.getTitle();
        System.out.println("Page Title: " + title);
    }

    @Test
    public void checkFlightDetails() {
        bookPage.getFlightDetails();
    }
}
