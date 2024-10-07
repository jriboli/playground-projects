package com.google;

import com.google.endpoints.AddressValidationAPI;
import com.google.endpoints.DirectionsAPI;
import com.google.payloads.Address;
import com.google.utility.ResponseValidator;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertNotNull;

public class AddressValidation_StructureTesting extends AddressValidationAPI {

    private Response res;

    @BeforeClass
    public void setup() {
        Address address = new Address();
        address.setAddressLines(List.of("13670 Ocana St"));
        address.setRegionCode("US");
        address.setPostalCode("90706");

        res = validateAddress(address);
    }

    @Test
    public void testTopLevelFields() {
        String verdict = res.jsonPath().getString("result.verdict");
        String address = res.jsonPath().getString("result.address");
        String geocode = res.jsonPath().getString("result.geocode");
        String metaData = res.jsonPath().getString("result.metadata");
        String uspsData = res.jsonPath().getString("result.uspsData");

        assertNotNull(verdict, "Verdict should not be empty");
        assertNotNull(address, "Address should not be empty");
        assertNotNull(geocode, "Geocode should not be empty");
        assertNotNull(metaData, "MetaData should not be empty");
        assertNotNull(uspsData, "UspsData should not be empty");

        ResponseValidator.validateBasicResponse(res);
    }

    @Test
    public void testAddressMarkedComplete() {
        res.then().body("result.verdict.addressComplete", equalTo(true));
    }

    @Test
    public void testMetaData() {
        res.then()
                .body("result.metadata.business", equalTo(false))
                .body("result.metadata.poBox", equalTo(false))
                .body("result.metadata.residential", equalTo(true));
    }
}
