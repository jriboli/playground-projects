package org.example.assignments.makemytrip;

import org.example.assignments.MakeMyTripBaseTest;
import org.testng.annotations.Test;

public class SearchPageTest extends MakeMyTripBaseTest {

    @Test
    public void searchDefaultCities() {
        String fromCity = searchPage.getSearchFromCity();
        String toCity = searchPage.getSearchToCity();
        System.out.println("From: " + fromCity + " To: " + toCity);
    }

    @Test
    public void setFromCity() {
        searchPage.setSearchFromCity("LAX");
        String fromCity = searchPage.getSearchFromCity();
        System.out.println("From: " + fromCity);
    }

    @Test
    public void setToCity() {
        searchPage.setSearchToCity("NYC");
        String toCity = searchPage.getSearchToCity();
        System.out.println("From: " + toCity);
    }

    @Test
    public void setDepartureDate() {
        searchPage.setDepartureDate();
    }

    @Test
    public void setReturnDate() {
        searchPage.setReturnDate();
    }

    @Test
    public void setTravellers() {
        searchPage.setTravellers(3,3,3, "Business");
    }

    @Test
    public void LAXtoNYCFlight() {
        searchPage.setSearchFromCity("LAX");
        searchPage.setSearchToCity("NYC");
        // Selecting ToCity automatically kicks you into Date Selection
        searchPage.setDepartureDate();
        searchPage.setReturnDate();
        searchPage.setTravellers(3,3,3, "Business");
    }
}
