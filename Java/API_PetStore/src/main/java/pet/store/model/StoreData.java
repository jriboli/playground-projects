package pet.store.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import pet.store.entity.Employee;
import pet.store.entity.Store;

@Data
@NoArgsConstructor
public class StoreData {

    private Long storeId;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String name;
    private String phone;
    private int numOfEmployees;

    public StoreData(Store store) {
        storeId = store.getStoreId();
        address = store.getAddress();
        city = store.getCity();
        state = store.getState();
        zip = store.getZip();
        name = store.getName();
        phone = store.getPhone();

        for(Employee employee : store.getEmployees()) {
            numOfEmployees++;
        }
    }
}
