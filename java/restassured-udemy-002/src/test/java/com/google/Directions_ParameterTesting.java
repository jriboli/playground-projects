package com.google;

import com.google.endpoints.DirectionsAPI;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class Directions_ParameterTesting extends DirectionsAPI {

    @Test
    public void getRouteWithAlternates() {
        Response res = DirectionsAPI.newDirections("Downey, CA", "Burbank, CA")
                .alternatives(true)
                .getDirections();
        int routes = res.jsonPath().getList("routes").size();
        //assertTrue(routes > 1, "Routes should be greater than 1");
        // Use MatcherAssert.assertThat() instead of JUnit's assertTrue() for more descriptive error messages.
        assertThat("Routes should be greater than 1", routes, greaterThan(1));
    }

    @Test
    public void getRouteViaBike() {
        Response res = DirectionsAPI.newDirections("Downey, CA", "Long Beach, CA")
                .mode("bicycling")
                .getDirections();
        String travelMode = res.jsonPath().getString("routes[0].legs[0].steps[0].travel_mode");

        // Extract all travel_mode values as a complex structure
        List<List<List<String>>> travelModesComplex = res.jsonPath().getList("routes.legs.steps.travel_mode");

        // Flatten the structure into a single list
        List<String> travelModesFlat = new ArrayList<>();
        for (List<List<String>> legs : travelModesComplex) {
            for (List<String> steps : legs) {
                travelModesFlat.addAll(steps);
            }
        }

        // Now `travelModesFlat` contains all travel_mode values in a single list
        System.out.println("Flat List of Travel Modes: " + travelModesFlat);

        assertThat(travelModesFlat, everyItem(equalTo("BICYCLING")));
    }

    @Test
    public void getRouteViaWalking() {
        Response res = DirectionsAPI.newDirections("Downey, CA", "Long Beach, CA")
                .mode("walking")
                .getDirections();
    }
}
