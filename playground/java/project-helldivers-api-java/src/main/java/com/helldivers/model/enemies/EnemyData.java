package com.helldivers.model.enemies;

import com.helldivers.entity.enemies.Enemy;
import com.helldivers.enums.enemies.Type;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EnemyData {
    private Long id;
    private String name;
    private String description;
    private Type type;

    public EnemyData(Enemy enemy) {
        this.id = enemy.getId();
        this.name = enemy.getName();
        this.description = enemy.getDescription();
        this.type = enemy.getType();
    }
}
