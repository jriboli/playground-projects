package petstore.pojos;

import lombok.Data;

@Data
public class PetTag {
    int id;
    String name;
}

//public enum PetTag {
//    FRIENDLY, TRAINED, VACCINATED, MICROCHIPPED;
//}