package airlines;

import RestUtils.RestUtils;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

// Common Airline API functionality
public class AirlineAPI {

    public Response createAirline(Map<String, Object> createAirlinePayload) {
        String endpoint = (String) Base.dataFromJsonFile.get("endpoint");
        return RestUtils.performPost(endpoint, createAirlinePayload, new HashMap<>());
    }
}
