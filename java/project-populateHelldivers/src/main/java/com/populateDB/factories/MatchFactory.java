package com.populateDB.factories;

import com.populateDB.enums.Difficulty;
import com.populateDB.enums.PlayerType;
import com.populateDB.models.Match;
import com.populateDB.models.Player;
import com.populateDB.models.WeaponFilters;
import com.populateDB.service.HelldiverWrapperService;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class MatchFactory {

    private static final HelldiverWrapperService service = new HelldiverWrapperService();

    private static final Map<String, Integer> ENEMY_PERCENTAGES = Map.of(
            "TERMINID", 50,
            "AUTOMATON", 50
    );

    public static Match createMatch(Player player) {
        String enemyType = chooseEnemyType();
        Difficulty difficulty = Difficulty.valueOf(chooseDifficulty(player.getType()));
        String objective = chooseObjective();
        String planet = choosePlanet(enemyType);
        String weapon = chooseWeapon(player.getType(), enemyType);
        Integer shotsFired = chooseShotsFired(player.getType(), difficulty);
        Integer shotsHit = chooseShotsHit(player.getType(), shotsFired);

        return Match.builder()
                .enemyType(enemyType)
                .difficulty(difficulty)
                .objective(objective)
                .planet(planet)
                .weapons(weapon)
                .shotsFired(shotsFired)
                .shotsHit(shotsHit)
                .build();
    }

    private static String chooseWeapon(PlayerType playerType, String enemyType){

        WeaponFilters filters = new WeaponFilters();
        switch(playerType){
            case NOOB:
                filters.setTrait("LIGHT_ARMOR_PENETRATING");
                filters.setMode("AUTOMATIC");
                break;
            case AVERAGE:
                filters.setTrait("LIGHT_ARMOR_PENETRATING");
                break;
            case BEAST:
                filters.setTrait("MEDIUM_ARMOR_PENETRATING");
                break;
            case CHEAT:
                break;
            default:
                // Should not happened
                break;
        }

        List<String> weapons = service.getWeapons(filters);
        // Create a Random object
        Random random = new Random();

        // Generate a random number between lower and upper bounds
        int randomNumber = random.nextInt(weapons.size());
        return weapons.get(randomNumber);

    }

    private static String chooseEnemyType(){
        int totalPercentage = ENEMY_PERCENTAGES.values().stream().mapToInt(Integer::intValue).sum();
        int rand = new Random().nextInt(totalPercentage);
        int cumulative = 0;

        for (Map.Entry<String, Integer> entry : ENEMY_PERCENTAGES.entrySet()) {
            cumulative += entry.getValue();
            if (rand < cumulative) {
                return entry.getKey();
            }
        }
        // Default return (should never be reached)
        return ENEMY_PERCENTAGES.keySet().iterator().next();
    }

    private static String choosePlanet(String enemyType) {
        List<String> planets = service.getPlanets(enemyType);

        // TBD
        // Only 1 planet at this point

        return planets.get(0);
    }
    private static String chooseObjective() {
        // TBD
        return "ERADICATE";
    }

    private static String chooseDifficulty(PlayerType playerType) {
        Random random = new Random();
        int randomNumber = 0;

        switch(playerType){
            case NOOB:
                randomNumber = random.nextInt(1,4);
                break;
            case AVERAGE:
                randomNumber = random.nextInt(3,7);
                break;
            case BEAST:
                randomNumber = random.nextInt(6,9);
                break;
            case CHEAT:
                randomNumber = random.nextInt(1,9);
                break;
            default:
                // Should not happened
                break;
        }

        return Difficulty.fromValue(randomNumber).toString();
    }

    private static Integer chooseShotsFired(PlayerType playerType, Difficulty difficulty) {
        int shotsFired = 0;
        Random random = new Random();
        int randomNumber = 0;
        int shotExponent = difficulty.value;

        switch(playerType){
            case NOOB:
                randomNumber = random.nextInt(75, 100);
                break;
            case AVERAGE:
                randomNumber = random.nextInt(100,150);
                break;
            case BEAST:
                randomNumber = random.nextInt(150,200);
                break;
            case CHEAT:
                randomNumber = random.nextInt(50,250);
                break;
            default:
                // Should not happened
                break;
        }

        shotsFired = randomNumber * shotExponent;

        return shotsFired;
    }

    private static Integer chooseShotsHit(PlayerType playerType, Integer shotsFired) {
        double shotsHit = 0;
        Random random = new Random();
        double randomNumber = 0;

        switch(playerType){
            case NOOB:
                randomNumber = random.nextInt(5,30);
                break;
            case AVERAGE:
                randomNumber = random.nextInt(20,50);
                break;
            case BEAST:
                randomNumber = random.nextInt(35,70);
                break;
            case CHEAT:
                randomNumber = random.nextInt(85,100);
                break;
            default:
                // Should not happened
                break;
        }

        shotsHit = shotsFired * (randomNumber/100);

        return (int)shotsHit;
    }
}
