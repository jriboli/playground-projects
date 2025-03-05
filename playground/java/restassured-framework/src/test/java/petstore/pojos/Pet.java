package petstore.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Pet {
    int id;
    PetCategory category;
    String name;
    String[] photoUrls;
    PetTag[] tags;
    String status;
}
