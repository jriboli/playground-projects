package petstore.endpoints;

import RestUtils.RestUtils;
import io.restassured.response.Response;
import petstore.pojos.Pet;

import java.util.HashMap;

public class PetAPI {

    // GET
    public Response getPets() {
        String endpoint = (String) Base.dataFromJsonFile.get("endpoint") + "pets";
        return RestUtils.performGet(endpoint, new HashMap<>());
    }

    public Response getPetById(int petId) {
        String endpoint = (String) Base.dataFromJsonFile.get("endpoint") + "pets/" + petId;
        return RestUtils.performGet(endpoint, new HashMap<>());
    }

    // POST
    public Response createPet(Pet pet) {
        String endpoint = (String) Base.dataFromJsonFile.get("endpoint") + "pets";
        return RestUtils.performPost(endpoint, pet, new HashMap<>());
    }

    // PUT
    public Response updatePet(int petId, Pet pet) {
        String endpoint = (String) Base.dataFromJsonFile.get("endpoint") + "pets/" + petId;
        return RestUtils.performPost(endpoint, pet, new HashMap<>());
    }


}
