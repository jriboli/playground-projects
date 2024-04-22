package org.example.assignments.makemytrip;

import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Page;

import java.util.List;

public class BookPage {

    public Page page;

    public BookPage(Page page) { this.page = page; }

    public String getTitle() {
        page.waitForTimeout(10000);
        return page.locator("div.pageHeader").innerText();
    }

    public void getFlightDetails() {
        page.waitForTimeout(5000);
        List<ElementHandle> flightsInfo = page.querySelectorAll("section.flightDetailBlk");
        System.out.println("Flight Info Count: " + flightsInfo.size());

        for(ElementHandle info : flightsInfo) {
            System.out.println(info.querySelector("h2").innerHTML());
        }
    }
}
