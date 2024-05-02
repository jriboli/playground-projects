package com.helldivers.populate.models.HelldiverAPI;

import com.helldivers.populate.enums.Mode;
import com.helldivers.populate.enums.Trait;
import lombok.Data;

import java.util.List;

@Data
public class Weapon {
    private String name;
    private List<Mode> modes;
    private List<Trait> traits;
}
