package com.populateDB.factories;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.populateDB.service.HelldiverWrapperService;
import com.populateDB.models.Player;
import com.populateDB.enums.PlayerType;
import com.populateDB.models.RandomUser.RandomUser;
import com.populateDB.models.RandomUser.Results;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Random;

public class PlayerFactory {
    private static final String genApiBaseUrl = "https://randomuser.me/api/";
    private static final Map<String, Integer> DEFAULT_PERCENTAGES = Map.of(
            "NOOB", 20,
            "AVERAGE", 50,
            "BEAST", 25,
            "CHEAT", 5
    );

    public static Player createPlayer() {
        Player player = genRandomPlayerInfo();
        return createPlayer(player.getName(), player.getEmail(), player.getGamerTag(), DEFAULT_PERCENTAGES);
    }

    public static Player createPlayer(String name, String email, String gamerTag, Map<String, Integer> percentages) {
        String type = chooseType(percentages);
        PlayerType playerType = PlayerType.valueOf(type);
        return new Player(name, email, gamerTag, playerType, null);
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

    private static Player genRandomPlayerInfo() {
        RestTemplate restTemplate = new RestTemplate();

        String response = restTemplate.getForObject(genApiBaseUrl, String.class);
        //System.out.println("API Response: " + response);

        ObjectMapper mapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            Results results = mapper.readValue(response, Results.class);
            //System.out.println("RESULTS: " + results.getResults().get(0).getLogin().getUsername());
            //System.out.println("RESULTS: " + results);

            return setPlayerFields(results);
        } catch(Exception ex) {
            System.out.println("Something Happened!!");
            System.out.println(ex.getMessage());
            System.out.println(ex.getStackTrace());
        }

        return null;
    }

    private static Player setPlayerFields(Results data) {
        Player player = new Player();
        RandomUser user = data.getResults().get(0);
        player.setName(user.getName().first + " " + data.getResults().get(0).getName().last);
        player.setEmail(user.getEmail());
        player.setGamerTag(user.getLogin().getUsername());

        return player;
    }
}
