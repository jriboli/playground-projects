package petstore.endpoints;

import RestUtils.RestUtils;
import io.restassured.response.Response;
import petstore.pojos.Store;

import java.util.HashMap;

public class StoreAPI {

    private final String baseEndpoint = (String) Base.dataFromJsonFile.get("endpoint") + "stores";

    // GET
    public Response getStores() {
        return RestUtils.performGet(baseEndpoint, new HashMap<>());
    }

    public Response getStoreById(int storeId) {
        String fullEndpoint = baseEndpoint + "/" + storeId;
        return RestUtils.performGet(fullEndpoint, new HashMap<>());
    }

    // POST
    public Response createStore(Store store) {
        return RestUtils.performPost(baseEndpoint, store, new HashMap<>());
    }
}
