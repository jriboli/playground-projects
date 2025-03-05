package petstore;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import petstore.endpoints.PetAPI;
import petstore.enums.PetStatus;
import petstore.factory.PetFactory;
import petstore.pojos.Pet;

import java.io.IOException;

import static org.testng.Assert.assertEquals;

public class PetTests extends PetAPI {

    @Test
    public void createPet() throws IOException {

        Pet randomPet = PetFactory.createRandomPet();
        Response response = createPet(randomPet);
        assertEquals(response.statusCode(), 200);
    }

    @Test
    public void createPetWithMinimal() {
        Pet newPet = new Pet();
        newPet.setName("Fido");

        Response response = createPet(newPet);
        assertEquals(response.statusCode(), 200);
    }

    @Test
    public void createPetWithDefinedID() {
        Pet randomPet = PetFactory.createRandomPet();
        randomPet.setId(1);

        Response response = createPet(randomPet);
        assertEquals(response.statusCode(), 200);
    }

    @Test
    public void createPetWithInvalidStatus() {
        Pet randomPet = PetFactory.createRandomPet();
        randomPet.setStatus(PetStatus.INVALID);
        Response response = createPet(randomPet);

        assertEquals(response.statusCode(), 500);
    }

}
