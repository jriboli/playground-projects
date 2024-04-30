package com.populateDB.factories;

import com.populateDB.models.Match;
import com.populateDB.models.Player;
import com.populateDB.service.HelldiverWrapperService;

import java.util.List;
import java.util.Map;

public class MatchFactory {

    private static HelldiverWrapperService service = new HelldiverWrapperService();

    private static final Map<String, Integer> DEFAULT_PERCENTAGES = Map.of(
            "TERMINID", 50,
            "AUTOMATON", 50
    );

    public static Match createMatch(Player player) {
        List<String> planets = service.getPlanets();
        List<String> objectives = service.getObjectives();
        List<String> weapons = service.getWeapons();


    }

    private static String chooseWeapon(){

    }

    private static String choosePlanet(Map<String, Integer> percentages){

    }

    private static String chooseObjective() {

    }

    private static String chooseDifficulty() {

    }
}
