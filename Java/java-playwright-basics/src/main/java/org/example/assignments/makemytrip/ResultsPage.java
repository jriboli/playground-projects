package org.example.assignments.makemytrip;

import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.JSHandle;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.example.assignments.entity.FlightCard;
import org.example.assignments.entity.FlightInfo;

import java.util.ArrayList;
import java.util.List;

public class ResultsPage {

    public Page page;

    public ResultsPage(Page page) { this.page = page; }

    public String getTitle() {
        return page.locator("p.font24.blackFont").innerText();
    }

    public String getCheapest() {
        Locator cheapest = page.locator("div.clusterTabHeadList.makeFlex").filter(new Locator.FilterOptions().setHas(page.getByText("Cheapest")));
        System.out.println(cheapest.innerText());

        return "";
    }

    public List<FlightCard> getFlightsList() {
        Locator content = page.locator("div.clusterContent");
        List<ElementHandle> flightCards = page.querySelectorAll("div.listingCard");

        System.out.println("Count: " + flightCards.size());

        ElementHandle recordOne = flightCards.get(0);
        System.out.println(recordOne.querySelector("div.clusterViewPrice").innerText());
        System.out.println(recordOne.querySelector("div.airlineName").innerText());

        ElementHandle recordTwo = flightCards.get(5);
        System.out.println(recordTwo.querySelector("div.clusterViewPrice").innerText());
        System.out.println(recordTwo.querySelector("div.airlineName").innerText());

        page.waitForTimeout(5000);

        List<FlightCard> fcList = new ArrayList<>();
        return fcList;
    }

    public FlightCard getFlightAtIndex(int index) {
        page.waitForSelector("div.clusterContent");

        Locator content = page.locator("div.clusterContent");
        List<ElementHandle> flightCards = page.querySelectorAll("div.listingCard");

        System.out.println("Listing Count: " + flightCards.size());
        if(index > flightCards.size()) {
            throw new IndexOutOfBoundsException("The index you have provided is out of bounds: " + index);
        }

        ElementHandle record = flightCards.get(index);
        FlightCard fc = new FlightCard();
        fc.flightPrice = record.querySelector("div.clusterViewPrice").innerText();

        // Expand Flight Details
        record.querySelector("span.viewFltDtlsCta").click();

        page.waitForTimeout(5000);
        List<ElementHandle> test = page.querySelectorAll("div.collapse.show");

        System.out.println("Test Count: " + test.size());

        // The Flight Details tab
        List<ElementHandle> detailTabs = test.get(0).querySelectorAll("div.tab-content");
        System.out.println("Detail Count: " + detailTabs.size());

        List<ElementHandle> flights = detailTabs.get(0).querySelector("div#flightDetailsTab-undefined-tabpane-1").querySelectorAll("div.flightDetails");
        fc.flights = new ArrayList<>();

        System.out.println("Flight Count: " + flights.size());
        for(ElementHandle flight : flights) {
            FlightInfo fi = new FlightInfo();
            fi.departure = flight.querySelectorAll("div.airlineDTInfoCol").get(0).innerText();
            fi.arrival = flight.querySelectorAll("div.airlineDTInfoCol").get(1).innerText();
            fi.duration = flight.querySelector("div.airlineDtlDuration").innerText();
            fi.airlineCode = flight.querySelector("div.flightDetailsRow").querySelectorAll("font").get(0).innerText();

            fc.flights.add(fi);
            System.out.println("FlightInfo: " + fi);
        }

        return fc;
    }
}
