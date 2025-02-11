package petstore.pojos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Store {
    Long storeId;
    String address;
    String city;
    String state;
    String zip;
    String name;
    String phone;
    int numOfEmployees;
}
