package com.google;

import com.google.endpoints.DirectionsAPI;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class Directions_StructureTesting extends DirectionsAPI {
    
    @Test
    public void basicDirections() {
        Response res = getBasicDirections("Downey, CA", "Bellflower, CA");
        res.then().statusCode(200);
    }
}
