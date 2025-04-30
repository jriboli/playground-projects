package com.woocommerce.util;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.PlaywrightException;

public class DebugUtil {

    public static void printLocatorInfo(Locator locator, String description) {
        try {
            int count = locator.count();
            System.out.println("---- DEBUG: " + description + " ----");
            System.out.println("Total matches: " + count);

            for (int i = 0; i < count; i++) {
                Locator element = locator.nth(i);
                System.out.println("Element #" + (i + 1));
                System.out.println("  Inner Text: " + element.innerText());
                System.out.println("  Is Visible: " + element.isVisible());
                System.out.println("  Is Enabled: " + element.isEnabled());
                System.out.println("  Tag Name  : " + element.evaluate("e => e.tagName"));
                System.out.println("  Classes   : " + element.getAttribute("class"));
                System.out.println("  ID        : " + element.getAttribute("id"));
                System.out.println("  Name      : " + element.getAttribute("name"));
                System.out.println("---------------------------------");
            }

        } catch (PlaywrightException e) {
            System.err.println("Error while debugging locator: " + e.getMessage());
        }
    }
}
