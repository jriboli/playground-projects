package pet.store.entity;

import jakarta.persistence.*;
import lombok.Data;
import pet.store.enums.PetCategory;
import pet.store.enums.PetStatus;
import pet.store.enums.PetTag;

@Entity
@Data
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long petId;

    private PetCategory category;
    private String name;
    private PetTag[] tags;
    private PetStatus status;

    @ManyToOne
    @JoinColumn(name = "store_id", insertable = false, updatable = false)
    private Store store;
}
