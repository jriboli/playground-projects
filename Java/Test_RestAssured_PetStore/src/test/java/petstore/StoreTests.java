package petstore;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import petstore.endpoints.StoreAPI;

import static org.testng.Assert.assertEquals;

public class StoreTests extends StoreAPI {

    @Test
    public void pullAllStores() {

        Response response = getStores();
        //assertEquals(response.statusCode(), 200);
        response.then().statusCode(400);
    }

    @Test
    public void pullSpecificStore() {
        Response response = getStoreById(1);
        response.then().statusCode(200);
    }

    @Test
    public void pullStoreWithInvalidStoreId() {
        Response response = getStoreById(-1);
        response.then().statusCode(500);
    }
}
