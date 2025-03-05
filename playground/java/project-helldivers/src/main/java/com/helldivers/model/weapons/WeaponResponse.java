package com.helldivers.model.weapons;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class WeaponResponse {
    private Integer num_of_weapons;
    private List<WeaponData> weapons;

    public WeaponResponse(List<WeaponData> data) {
        weapons = data;
        num_of_weapons = data.size();
    }

    public WeaponResponse(WeaponData data) {
        List<WeaponData> tempData = new ArrayList<>();
        tempData.add(data);

        weapons = tempData;
        num_of_weapons = 1;
    }
}
