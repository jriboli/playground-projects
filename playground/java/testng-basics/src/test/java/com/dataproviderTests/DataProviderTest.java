package com.dataproviderTests;

import com.util.TestUtil;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;

public class DataProviderTest {

    @BeforeMethod
    public void setup() {

    }

    @DataProvider
    public Iterator<Object[]> getTestData(){
        ArrayList<Object[]> excelData = TestUtil.getDataFromExcel();
        return excelData.iterator();
    }

    @Test(dataProvider = "getTestData")
    public void siteLogin(String firstName, String lastName, String address,
          String city, String state, String zipCode, String emailAddress) {

        System.out.println("Record Details ----" +
                "\n FirstName: " + firstName +
                "\n LastName: " + lastName +
                "\n Address: " + address +
                "\n City: " + city +
                "\n State: " + state +
                "\n ZipCode: " + zipCode +
                "\n Email: " + emailAddress + "\n");
    }

    @AfterMethod
    public void tearDown() {

    }
}
