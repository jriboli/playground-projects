package com.helldivers.model;

import com.helldivers.entity.weapons.Weapon;

import java.util.ArrayList;
import java.util.List;

public class WeaponResponse {
    private List<WeaponData> weapons;

    public WeaponResponse(List<WeaponData> data) {
        weapons = data;
    }

    public WeaponResponse(WeaponData data) {
        List<WeaponData> tempData = new ArrayList<>();
        tempData.add(data);

        weapons = tempData;
    }
}
