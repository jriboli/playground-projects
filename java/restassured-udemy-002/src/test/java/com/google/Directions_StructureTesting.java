package com.google;

import com.google.endpoints.DirectionsAPI;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.is;
import static org.testng.Assert.*;

public class Directions_StructureTesting extends DirectionsAPI {

    private Response res;

    @BeforeClass
    public void setup() {
        res = DirectionsAPI.newDirections("Downey, CA", "Bellflower, CA").getDirections();
    }

    @Test
    public void testTopLevelFields() {
        String geocode = res.jsonPath().getString("geocoded_waypoints");
        String routes = res.jsonPath().getString("routes");
        String status = res.jsonPath().getString("status");

        assertNotNull(geocode, "Geocode should not be empty");
        assertNotNull(routes, "Routes should not be empty");
        assertNotNull(status, "Status should not be empty");
    }

    @Test
    public void testGeocodeWaypoints() {
        List<String> statuses = res.jsonPath().getList("geocoded_waypoints.geocoder_status");
        assertThat(statuses, everyItem(is("OK")));
    }

    @Test
    public void testRoutesFields() {
        int routeSize = res.jsonPath().getList("routes").size();
        assertTrue(routeSize > 0, "Routes list should not be empty");
    }

    @Test
    public void testLegsFields() {
        List<String> startAddresses = res.jsonPath().getList("routes.legs.start_address", String.class);

        // Testing the List
        System.out.println(startAddresses.getClass().getName());
        startAddresses.forEach(System.out::println);

        //for(String address : startAddresses) {
        //    assertEquals(address, "Downey, CA", "Start address should match the expected value");
        //}

        // Using Hamcrest Matchers
        assertThat(startAddresses, everyItem(is("Downey, CA, USA")));

    }

    @Test
    public void testResponseStatusCode() {
        res.then().statusCode(200);
    }
}
