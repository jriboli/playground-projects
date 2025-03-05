package org.example.assignments.tizag;

import org.example.assignments.TizagBaseTest;
import org.testng.annotations.Test;

import java.util.List;

public class HomePageTest extends TizagBaseTest {

    @Test
    public void checkTitle() {
        homePage.clickOnHTMLTutorial();
        homePage.clickOnCheckBoxesLink();
        String title = homePage.getTitle();

        System.out.println("Title: " + title);
    }

    @Test
    public void checkBoxesTest() {
        homePage.clickOnHTMLTutorial();
        homePage.clickOnCheckBoxesLink();

        homePage.selectCheckboxForm(List.of(false, false, true, true));
        homePage.selectCheckboxForm2(List.of(false, false, true, true));
    }
}
