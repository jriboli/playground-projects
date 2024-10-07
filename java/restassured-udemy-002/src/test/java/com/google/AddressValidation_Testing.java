package com.google;

import com.google.endpoints.AddressValidationAPI;
import com.google.payloads.Address;
import com.google.payloads.AddressValidationRequest;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;

public class AddressValidation_Testing extends AddressValidationAPI {

    @Test
    public void validBasicAddress() {
        Address address = new Address();
        address.setAddressLines(List.of("13670 Ocana St"));
        address.setRegionCode("US");
        address.setPostalCode("90706");

        Response res = validateAddress(address);

        res.then().statusCode(200);
    }
}
