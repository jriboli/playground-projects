package com.binaryNomad.utils;

import io.restassured.response.Response;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ResponseValidator {

    public static void validateBasicResponse(Response response) {

        assertThat(response.getStatusCode(), is(200));
        assertThat(response.getBody().asString(), is(notNullValue()));
        assertThat(response.getContentType(), containsString("application/json"));
        assertThat(response.getTime(), lessThan(3000L)); // Under 3 seconds
    }
}
