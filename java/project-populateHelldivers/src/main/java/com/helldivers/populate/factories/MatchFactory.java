package com.helldivers.populate.factories;

import com.helldivers.populate.enums.Mode;
import com.helldivers.populate.enums.Trait;
import com.helldivers.populate.models.HelldiverAPI.Planet;
import com.helldivers.populate.models.HelldiverAPI.Weapon;
import com.helldivers.populate.models.Player;
import com.helldivers.populate.models.WeaponFilters;
import com.helldivers.populate.service.HelldiverWrapperService;
import com.helldivers.populate.enums.Difficulty;
import com.helldivers.populate.enums.PlayerType;
import com.helldivers.populate.models.Match;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MatchFactory {

    private static final Map<String, Integer> ENEMY_PERCENTAGES = Map.of(
            "TERMINID", 50,
            "AUTOMATON", 50
    );

    private static List<Weapon> weaponList;
    private static List<Planet> planetList;

    public static Match createMatch(Player player, List<Weapon> weapons, List<Planet> planets) {
        weaponList = weapons;
        planetList = planets;

        String enemyType = chooseEnemyType();
        Difficulty difficulty = Difficulty.valueOf(chooseDifficulty(player.getType()));
        String objective = chooseObjective();
        String planet = choosePlanet(enemyType);
        String weapon = chooseWeapon(player.getType());
        Integer shotsFired = chooseShotsFired(player.getType(), difficulty);
        Integer shotsHit = chooseShotsHit(player.getType(), shotsFired);

        return Match.builder()
                .enemyType(enemyType)
                .difficulty(difficulty)
                .objective(objective)
                .location(planet)
                .weapons(weapon)
                .shotsFired(shotsFired)
                .shotsHit(shotsHit)
                .build();
    }

    private static String chooseWeapon(PlayerType playerType){

        List<Weapon> weapons = new ArrayList<>();
        switch(playerType){
            case NOOB:
                weapons = weaponList.stream()
                        .filter(w -> w.getModes().contains(Mode.AUTOMATIC))
                        .filter(w -> w.getTraits().contains(Trait.LIGHT_ARMOR_PENETRATING))
                        .toList();
                break;
            case AVERAGE:
                weapons = weaponList.stream()
                        .filter(w -> w.getTraits().contains(Trait.LIGHT_ARMOR_PENETRATING))
                        .toList();
                break;
            case BEAST:
                weapons = weaponList.stream()
                        .filter(w -> w.getTraits().contains(Trait.MEDIUM_ARMOR_PENETRATING))
                        .toList();
                break;
            case CHEAT:
                weapons = weaponList;
                break;
            default:
                // Should not happened
                break;
        }

        // Create a Random object
        Random random = new Random();

        // Generate a random number between lower and upper bounds
        int randomNumber = random.nextInt(weapons.size());
        List<String> weaponNames = weapons.stream()
                .map(Weapon::getName)
                .toList();
        return weaponNames.get(randomNumber);

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
        // Create a Random object
        Random random = new Random();

        // Generate a random number between lower and upper bounds
        int randomNumber = random.nextInt(planetList.size());
        List<String> planetNames = planetList.stream()
                .map(Planet::getName)
                .toList();
        return planetNames.get(randomNumber);
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
