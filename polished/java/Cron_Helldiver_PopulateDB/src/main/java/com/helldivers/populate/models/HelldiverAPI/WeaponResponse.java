package com.helldivers.populate.models.HelldiverAPI;

import lombok.Data;

import java.util.List;

@Data
public class WeaponResponse {
    private Integer num_of_weapons;
    private List<Weapon> weapons;
}
