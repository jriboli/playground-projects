package org.example.assignments.makemytrip;

import org.example.assignments.BaseTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ResultsPageTest extends BaseTest {

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
        resultsPage.getFlightAtIndex(0);
    }
}
