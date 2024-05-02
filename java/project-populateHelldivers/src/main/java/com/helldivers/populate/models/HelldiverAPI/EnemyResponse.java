package com.helldivers.populate.models.HelldiverAPI;

import lombok.Data;

import java.util.List;

@Data
public class EnemyResponse {
    private List<Enemy> enemies;
}
