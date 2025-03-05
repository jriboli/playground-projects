package com.helldivers.model.weapons;

import com.helldivers.entity.weapons.Weapon;
import com.helldivers.enums.weapons.Mode;
import com.helldivers.enums.weapons.Position;
import com.helldivers.enums.weapons.Trait;
import com.helldivers.enums.weapons.Type;
import lombok.Data;

import java.util.Set;

@Data
public class WeaponData {
    private Long id;
    private String name;
    private String description;
    private Type type;
    private Integer damage;
    private Integer capacity;
    private Integer recoil;
    private Integer fire_rate;
    private Position position;
    private Set<Mode> modes;
    private Set<Trait> traits;

    public WeaponData(Weapon data) {

        this.id = data.getId();
        this.name = data.getName();
        this.description = data.getDescription();
        this.type = data.getType();
        this.damage = data.getDamage();
        this.capacity = data.getCapacity();
        this.recoil = data.getRecoil();
        this.fire_rate = data.getFireRate();
        this.position = data.getPosition();
        this.modes = data.getModes();
        this.traits = data.getTraits();
    }
}
