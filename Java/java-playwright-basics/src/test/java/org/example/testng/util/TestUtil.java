package org.example.testng.util;

import java.util.ArrayList;

public class TestUtil {
    static Xls_Reader reader;

    public static ArrayList<Object[]> getDataFromExcel() {
        ArrayList<Object[]> myData = new ArrayList<>();
        try {
            reader = new Xls_Reader(
                    System.getProperty("user.dir")
                    + "/src/test/resources/data/TestNG_Login_Parameters.xlsx"
            );
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        for(int rowCnt = 2; rowCnt <= reader.getRowCount("Sheet1"); rowCnt++) {
            String firstName = reader.getCellData("Sheet1", "firstName", rowCnt);
            String lastName = reader.getCellData("Sheet1", "lastName", rowCnt);
            String address = reader.getCellData("Sheet1", "address", rowCnt);
            String city = reader.getCellData("Sheet1", "city", rowCnt);
            String state = reader.getCellData("Sheet1", "state", rowCnt);
            String zipCode = reader.getCellData("Sheet1", "zipCode", rowCnt);
            String emailAddress = reader.getCellData("Sheet1", "emailAddress", rowCnt);

            myData.add(new Object[]{firstName, lastName, address, city, state, zipCode, emailAddress});
        }

        return myData;
    }
}
