package org.example.assignments.tizag;

import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import java.util.List;

public class HomePage {
    public Page page;

    public HomePage(Page page) { this.page = page; }

    public void clickOnHTMLTutorial() {
        page.locator("//a[contains(text(),'HTML Tutorial')]").click();
    }

    public void clickOnCheckBoxesLink() {
        page.locator("//a[contains(text(),'HTML - Checkboxes')]").click();
    }

    public String getTitle() {
        List<Locator> elements = page.locator("//table[@class='main']").locator("h1").all();

        for(Locator element : elements) {
            System.out.println("Text: " + element.innerText());
        }

        return "";
    }

    public void selectCheckboxForm( List<Boolean> checkBoxValues) {

        page.waitForTimeout(5000);
        System.out.println("Test Count: " + page.locator("div.display").all().size());

        List<Locator> chechBoxes = page.locator("//table[@class='main']").locator("div.display").all().get(0).getByRole(AriaRole.CHECKBOX).all();
        System.out.println("Num of Checkboxes: " + chechBoxes.size());

        for(int i = 0; i < chechBoxes.size(); i++) {
            if (checkBoxValues.get(i) && !chechBoxes.get(i).isChecked()) {
                chechBoxes.get(i).check();
                System.out.println("Checked: " + chechBoxes.get(i).getAttribute("value"));
            } else if (!checkBoxValues.get(i) && chechBoxes.get(i).isChecked()) {
                chechBoxes.get(i).uncheck();
                System.out.println("Unchecked: " + chechBoxes.get(i).getAttribute("value"));
            }
        }
    }

    public void selectCheckboxForm2( List<Boolean> checkBoxValues) {

        page.waitForTimeout(5000);
        System.out.println("Test Count: " + page.locator("div.display").all().size());

        List<Locator> chechBoxes = page.locator("//table[@class='main']").locator("div.display").all().get(1).getByRole(AriaRole.CHECKBOX).all();
        System.out.println("Num of Checkboxes: " + chechBoxes.size());

        for(int i = 0; i < chechBoxes.size(); i++) {
            if (checkBoxValues.get(i) && !chechBoxes.get(i).isChecked()) {
                chechBoxes.get(i).check();
                System.out.println("Checked: " + chechBoxes.get(i).getAttribute("value"));
            } else if (!checkBoxValues.get(i) && chechBoxes.get(i).isChecked()) {
                chechBoxes.get(i).uncheck();
                System.out.println("Unchecked: " + chechBoxes.get(i).getAttribute("value"));
            }
        }
    }
}
