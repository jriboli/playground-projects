package com.helldivers.populate.factories;

import com.helldivers.populate.enums.EnemyType;
import com.helldivers.populate.models.HelldiverAPI.Enemy;
import com.helldivers.populate.models.Match;
import com.helldivers.populate.models.Player;
import com.helldivers.populate.service.HelldiverWrapperService;
import com.helldivers.populate.models.Kill;

import java.util.List;
import java.util.Random;

public class KillFactory {

    private static final HelldiverWrapperService service = new HelldiverWrapperService();
    private static List<Enemy> enemyList;
    public static Kill createKill(Player player, Match match, List<Enemy> enemies) {
        enemyList = enemies;

        String enemy = chooseEnemy(match.getEnemyType());
        String weapon = match.getWeapons();
        String timeStamp = chooseTimeStamp();
        // TBD
        return Kill.builder()
                .enemy(enemy)
                .weapon(weapon)
                .timeOfKill(timeStamp)
                .build();
    }

    private static String chooseEnemy(String enemyType) {
        Random random = new Random();

        List<String> enemyName = enemyList.stream()
                .filter(e -> e.getType().equals(EnemyType.valueOf(enemyType)))
                .map(Enemy::getName)
                .toList();
        int randomNumber = random.nextInt(enemyName.size());
        return enemyName.get(randomNumber);
    }

    private static String chooseTimeStamp() {
        Random random = new Random();
        int randomNumber = random.nextInt(1, 900000);
        return String.valueOf(randomNumber);
    }
}
