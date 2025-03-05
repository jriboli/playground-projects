package com.binaryNomad;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class RestAssuredConfig {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://gorest.co.in/public/v2";
    }
}
