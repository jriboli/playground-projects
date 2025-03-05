package org.example.testng;

import org.example.testng.dataproviders.LoginInfo;
import org.example.testng.util.TestUtil;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;

public class DataProviderTest {

    @DataProvider
    public static Object[][] getData() {

        Object[][] data = new Object[2][3];

        return data;
    }

    @Test(dataProvider = "getData")
    public void readFromDataProviderTest(String username, String password, String is_correct) {
        System.out.println(username + " : " + password + " ---- " + is_correct);
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

    @Test(dataProviderClass = LoginInfo.class,dataProvider = "dp1")
    public void testData(String username, String password) {
        System.out.println(username + " : " + password);
    }
}
