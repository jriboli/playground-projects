package com.populateDB.players;

import org.springframework.web.client.RestTemplate;

public class PlayerService {

    private final String apiBaseUrl = "";
    private final String genApiBaseUrl = "https://randomuser.me/api/";

    public Player genRandomPlayerInfo() {
        RestTemplate restTemplate = new RestTemplate();

        String response = restTemplate.getForObject(genApiBaseUrl, String.class);
        System.out.println("API Response: " + response);

        return null;
    }
    public void savePlayer(Player player) {

    }
}
