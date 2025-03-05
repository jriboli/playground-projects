package com.binaryNomad.endpoints;

import com.binaryNomad.config.ConfigLoader;
import com.binaryNomad.utils.RestRequestBuilder;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Properties;

public class DirectionsAPI {

//    String baseEndpoint = "https://maps.googleapis.com/maps/api/";
//    Properties props;
//
//    public DirectionsAPI() {
//        try {
//            props = ConfigLoader.loadProperties("src/test/resources/config.properties");
//        } catch (Exception ex) {
//            props = null;
//        }
//    }
//
//    public Response getBasicDirections(String origin, String destination) {
//        return getDirectionWithOptionals(origin, destination, null, null, null);
//    }
//
//    public Response getDirectionWithOptionals(String origin, String destination, String avoid, String mode, String alternates) {
//        String fullEndpoint = baseEndpoint + "directions/json?";
//
//        HashMap<String, String> parameters = new HashMap<>();
//        parameters.put("origin", origin);
//        parameters.put("destination", destination);
//        parameters.put("avoid", avoid);
//        parameters.put("mode", mode);
//        parameters.put("alternatives", alternates);
//        parameters.put("key", props.getProperty("api.key"));
//
//        return RestUtils.performGet(fullEndpoint, parameters);
//    }

    // Inner Builder class for DirectionsAPI
    public static class DirectionsBuilder{
        String baseEndpoint = "https://maps.googleapis.com/maps/api/";
        Properties props;

        // Mandator Fields
        private String origin;
        private String destination;
        // Optional Fields
        private String avoid;
        private String mode;
        private boolean alternatives;

        public DirectionsBuilder(String origin, String destination) {
            this.origin = origin;
            this.destination = destination;

            try {
                props = ConfigLoader.loadProperties("src/test/resources/config.properties");
            } catch (Exception ex) {
                props = null;
            }
        }

        public DirectionsBuilder avoid(String avoid) {
            this.avoid = avoid;
            return this;
        }

        public DirectionsBuilder mode(String mode) {
            this.mode = mode;
            return this;
        }

        public DirectionsBuilder alternatives(boolean alternatives) {
            this.alternatives = alternatives;
            return this;
        }

        public Response getDirections() {
            String fullEndpoint = baseEndpoint + "directions/json?";

            HashMap<String, String> parameters = new HashMap<>();
            parameters.put("origin", origin);
            parameters.put("destination", destination);
            parameters.put("avoid", avoid);
            parameters.put("mode", mode);
            parameters.put("alternatives", alternatives + "");
            parameters.put("key", props.getProperty("api.key"));

            //return RestRequestBuilder.performGet(null, fullEndpoint, parameters);
            return new RestRequestBuilder()
                    .withEndpoint(fullEndpoint)
                    .withQueryParams(parameters)
                    .performGet();
        }
    }

    // Static method to initiate the build with mandatory fields
    public static DirectionsBuilder newDirections(String origin, String destination) {
        return new DirectionsBuilder(origin, destination);
    }
}
