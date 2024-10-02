package com.google.endpoints;

import Config.ConfigLoader;
import RestUtils.RestUtils;
import io.restassured.response.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

public class DirectionsAPI {

    String baseEndpoint = "https://maps.googleapis.com/maps/api/";
    Properties props;

    public DirectionsAPI() {
        try {
            props = ConfigLoader.loadProperties("src/test/resources/config.properties");
        } catch (Exception ex) {
            props = null;
        }
    }

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
        parameters.put("key", props.getProperty("api.key"));

        return RestUtils.performGet(fullEndpoint, parameters);
    }
}
