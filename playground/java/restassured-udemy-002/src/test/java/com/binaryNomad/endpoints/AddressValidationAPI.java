package com.binaryNomad.endpoints;

import com.binaryNomad.config.ConfigLoader;
import com.binaryNomad.models.Address;
import com.binaryNomad.models.AddressValidationRequest;
import com.binaryNomad.utils.RestRequestBuilder;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Properties;

public class AddressValidationAPI {
    String baseEndpoint = "https://addressvalidation.googleapis.com/v1:";
    Properties props;

    public AddressValidationAPI() {
        try {
            props = ConfigLoader.loadProperties("src/test/resources/config.properties");
        } catch (Exception ex) {
            props = null;
        }
    }

    public Response validateAddress(Address address) {
        String fullEndpoint = baseEndpoint + "validateAddress";

        HashMap<String, String> headers = new HashMap<>();
        headers.put("content-type", "application/json");

        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("key", props.getProperty("api.key.addressvalidation"));

        AddressValidationRequest avr = new AddressValidationRequest();
        avr.setAddress(address);

        //return RestRequestBuilder.performPost(null, fullEndpoint, avr, headers, parameters, false);
        return new RestRequestBuilder()
                .withEndpoint(fullEndpoint)
                .withRequestBody(avr)
                .withHeaders(headers)
                .withQueryParams(parameters)
                .enableUrlEncoding(false)
                .performPost();
    }
}
