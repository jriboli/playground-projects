package org.example.assignments.makemytrip;

import org.example.assignments.MakeMyTripBaseTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ResultsPageTest extends MakeMyTripBaseTest {

    private ResultsPage resultsPage;

    @BeforeTest
    public void startSearch() {
        searchPage.setSearchFromCity("LAX");
        searchPage.setSearchToCity("NYC");
        // Selecting ToCity automatically kicks you into Date Selection
        searchPage.setDepartureDate();
        searchPage.setReturnDate();
        searchPage.setTravellers(3,3,3, "Business");

        resultsPage = searchPage.doSearch();
    }
    @Test
    public void checkTitleTest() {
        System.out.println("Results Title: " + resultsPage.getTitle());
    }

    @Test
    public void checkCheapestTest() {
        resultsPage.getCheapest();
    }

    @Test void checkNonStopTest() {

    }

    @Test
    public void checkPreferredTest() {

    }

    @Test
    public void checkAllFlightDetails() {
        resultsPage.getFlightsList();
    }

    @Test
    public void checkFirstFlightDetail() {
        resultsPage.getFlightInfoAtIndex(0);
    }

    @Test
    public void bookFlightTest() {
        resultsPage.bookFlightAtIndex(0);
    }
}
