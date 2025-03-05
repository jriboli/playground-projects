package com.helldivers.populate.models.HelldiverAPI;

import com.helldivers.populate.enums.EnemyType;
import lombok.Data;

@Data
public class Enemy {
    private String name;
    private EnemyType type;
}
