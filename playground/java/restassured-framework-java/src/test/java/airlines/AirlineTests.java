package airlines;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Map;

import static org.testng.Assert.assertEquals;

public class AirlineTests extends AirlineAPI {

    @Test
    public void createAirline() throws IOException {

        Map<String, Object> payload = Payloads.getCreateAirlinePayloadFromMap("14574324", "Deadpool Airlines", "US", "Deadpool Airlines", "Thwip Thwip", "New York", "www.deadpoolairlines.com", "1905" );
        Response response = createAirline(payload);
        assertEquals(response.statusCode(), 200);

    }
}
