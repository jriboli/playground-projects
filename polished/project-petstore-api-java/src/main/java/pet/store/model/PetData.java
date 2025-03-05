package pet.store.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import pet.store.entity.Pet;
import pet.store.enums.PetCategory;
import pet.store.enums.PetStatus;
import pet.store.enums.PetTag;

@Data
@NoArgsConstructor
public class PetData {

    private Long petId;
    private PetCategory category;
    private String name;
    private PetTag[] tags;
    private PetStatus status;

    public PetData(Pet pet) {
        petId = pet.getPetId();
        category = pet.getCategory();
        name = pet.getName();
        tags = pet.getTags();
        status = pet.getStatus();
    }
}
