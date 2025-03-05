package petstore.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import petstore.enums.PetCategory;
import petstore.enums.PetStatus;
import petstore.enums.PetTag;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pet {
    int id;
    PetCategory category;
    String name;
    PetTag[] tags;
    PetStatus status;
}
