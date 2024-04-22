package org.example.testng.dataproviders;

import org.testng.annotations.DataProvider;

public class LoginInfo {

    @DataProvider(name="dp1")
    public static Object[][] getDataOne() {
        Object[][] data = new Object[2][2];

        data[0][0] = "rocket";
        data[0][1] = "pa55w0rd";

        data[1][0] = "starlord";
        data[1][1] = "adm1n";

        return data;
    }
}
