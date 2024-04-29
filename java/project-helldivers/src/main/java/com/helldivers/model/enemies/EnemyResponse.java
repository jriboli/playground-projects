package com.helldivers.model.enemies;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class EnemyResponse {
    private Integer num_of_enemies;
    private List<EnemyData> enemies;

    public EnemyResponse(List<EnemyData> data) {
        enemies = data;
        num_of_enemies = data.size();
    }

    public EnemyResponse(EnemyData data) {
        List<EnemyData> tempData = new ArrayList<>();
        tempData.add(data);

        enemies = tempData;
        num_of_enemies = 1;
    }
}
