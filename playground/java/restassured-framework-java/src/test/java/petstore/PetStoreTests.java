package petstore;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import petstore.pojos.Pet;

import java.io.IOException;

import static org.testng.Assert.assertEquals;

public class PetStoreTests extends PetStoreAPI{

    @Test
    public void createPet() throws IOException {

        Pet randomPet = PetFactory.createRandomPet();
        Response response = createPet(randomPet);
        assertEquals(response.statusCode(), 200);
    }

}
