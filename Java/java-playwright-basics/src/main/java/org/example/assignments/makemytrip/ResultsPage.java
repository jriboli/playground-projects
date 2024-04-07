package org.example.assignments.makemytrip;

import com.microsoft.playwright.*;
import org.example.assignments.entity.FlightCard;
import org.example.assignments.entity.FlightInfo;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

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

    public FlightCard getFlightInfoAtIndex(int index) {

        FlightCard fc = new FlightCard();
        ElementHandle record = findFlightCardAtIndex(index);
        fc.flightPrice = record.querySelector("div.clusterViewPrice").innerText();
        fc.flights = new ArrayList<>();

        List<ElementHandle> flights = findFlightDetailsAtIndex(index);
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

    private List<ElementHandle> findFlightDetailsAtIndex(int index) {
        ElementHandle record = findFlightCardAtIndex(index);

        // Expand Flight Details
        record.querySelector("span.viewFltDtlsCta").click();
        List<ElementHandle> test = page.querySelectorAll("div.collapse.show");

        // The Flight Details tab
        List<ElementHandle> detailTabs = test.get(0).querySelectorAll("div.tab-content");
        return detailTabs.get(0).querySelector("div#flightDetailsTab-undefined-tabpane-1").querySelectorAll("div.flightDetails");
    }

    private ElementHandle findFlightCardAtIndex(int index) {
        page.waitForSelector("div.clusterContent");

        List<ElementHandle> flightCards = page.querySelectorAll("div.listingCard");

//        System.out.println("Listing Count: " + flightCards.size());
        if(index > flightCards.size()) {
            throw new IndexOutOfBoundsException("The index you have provided is out of bounds: " + index);
        }

        return flightCards.get(index);
    }

    public BookPage bookFlightAtIndex(int index) {
        CompletableFuture<Page> newPageFuture = new CompletableFuture<>();
        BrowserContext browserContext = page.context();
        browserContext.onPage(newPageFuture::complete);


        ElementHandle record = findFlightCardAtIndex(index);
        record.querySelector("(//button[contains(@class,'lato-black button')])[1]").click();

        page.waitForTimeout(5000);

//        System.out.println("Visible: " + page.locator("div.multifareOuter").isVisible());
//        Locator multifarePopup = page.locator("div.multifareOuter");
//
//        List<ElementHandle> fares = page.querySelectorAll("div.multifareCard");
//        System.out.println("Multifare Counter: " + fares.size());
//
//        for(ElementHandle fare : fares) {
//            System.out.println("Info: " + fare.querySelector("label.pointer").innerText());
//        }



        //newPageFuture.join();

        return new BookPage(newPageFuture.join());
    }
}
