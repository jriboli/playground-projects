package com.populateDB.players;

import com.populateDB.players.PlayerService;

import java.util.Map;
import java.util.Random;

public class PlayerFactory {

    private static PlayerService service;
    private static final Map<String, Integer> DEFAULT_PERCENTAGES = Map.of(
            "NOOB", 20,
            "AVERAGE", 50,
            "BEAST", 25,
            "CHEAT", 5
    );

    public static Player createPlayer() {
        Player player = service.genRandomPlayerInfo();
        return createPlayer(player.getName(), player.getEmail(), player.getGamerTag(), DEFAULT_PERCENTAGES);
    }

    public static Player createPlayer(String name, String email, String gamerTag, Map<String, Integer> percentages) {
        String type = chooseType(percentages);
        PlayerType playerType = PlayerType.valueOf(type);
        return new Player(name, email, gamerTag, playerType);
    }

    private static String chooseType(Map<String, Integer> percentages) {
        int totalPercentage = percentages.values().stream().mapToInt(Integer::intValue).sum();
        int rand = new Random().nextInt(totalPercentage);
        int cumulative = 0;

        for (Map.Entry<String, Integer> entry : percentages.entrySet()) {
            cumulative += entry.getValue();
            if (rand < cumulative) {
                return entry.getKey();
            }
        }
        // Default return (should never be reached)
        return percentages.keySet().iterator().next();
    }
}
