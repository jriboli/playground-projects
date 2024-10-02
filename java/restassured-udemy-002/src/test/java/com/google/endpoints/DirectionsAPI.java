package com.google.endpoints;

import RestUtils.RestUtils;
import io.restassured.response.Response;

import java.util.HashMap;

public class DirectionsAPI {

    String baseEndpoint = "https://maps.googleapis.com/maps/api/";

    public Response getBasicDirections(String origin, String destination) {
        return getDirectionWithOptionals(origin, destination, null, null);
    }

    public Response getDirectionWithOptionals(String origin, String destination, String avoid, String mode) {
        String fullEndpoint = baseEndpoint + "directions/json?";

        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("origin", origin);
        parameters.put("destination", destination);
        parameters.put("avoid", avoid);
        parameters.put("mode", mode);
        parameters.put("key", "");

        return RestUtils.performGet(fullEndpoint, parameters);
    }
}
