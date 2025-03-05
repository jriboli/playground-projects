package petstore;

import RestUtils.RestUtils;
import io.restassured.response.Response;
import petstore.pojos.Pet;

import java.util.HashMap;

public class PetStoreAPI {

    // GET
    public Response getPets() {
        String endpoint = (String) Base.dataFromJsonFile.get("endpoint") + "pet/findByStatus?";
        return RestUtils.performGet(endpoint, new HashMap<>());
    }

    // POST
    public Response createPet(Pet pet) {
        String endpoint = (String) Base.dataFromJsonFile.get("endpoint") + "pet";
        return RestUtils.performPost(endpoint, pet, new HashMap<>());
    }


}
