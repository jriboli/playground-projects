package com.populateDB.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.populateDB.models.HelldiverAPI.*;
import com.populateDB.models.Player;
import com.populateDB.models.RandomUserAPI.Results;
import com.populateDB.models.WeaponFilters;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

public class HelldiverWrapperService {

    private final String apiBaseUrl = "http://localhost:9000/api/v1/";

    public List<String> getWeapons(WeaponFilters filters) {

        RestTemplate restTemplate = new RestTemplate();

        String response = restTemplate.getForObject(apiBaseUrl + "weapons", String.class);
        //System.out.println("API Response: " + response);

        ObjectMapper mapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            WeaponResponse wResponse = mapper.readValue(response, WeaponResponse.class);
            //System.out.println("RESULTS: " + results.getResults().get(0).getLogin().getUsername());
            //System.out.println("RESULTS: " + results);

            List<String> results = wResponse.getWeapons().stream()
                    .map(Weapon::getName)
                    .toList();

            return results;

        } catch(Exception ex) {
            System.out.println("Something Happened!!");
            System.out.println(ex.getMessage());
            System.out.println(ex.getStackTrace());
        }

        return null;
    }

    public List<String> getPlanets(String planetType) {
        RestTemplate restTemplate = new RestTemplate();

        String response = restTemplate.getForObject(apiBaseUrl + "match/planets?planetType={s1}", String.class, planetType);
        ObjectMapper mapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            PlanetResponse pResponse = mapper.readValue(response, PlanetResponse.class);

            return pResponse.getPlanets().stream()
                    .map(Planet::getName)
                    .toList();
        } catch(Exception ex) {
            System.out.println("Something Happened!!");
            System.out.println(ex.getMessage());
            System.out.println(ex.getStackTrace());
        }

        return null;
    }

    public List<String> getEnemies(String enemyType) {
        RestTemplate restTemplate = new RestTemplate();

        String response = restTemplate.getForObject(apiBaseUrl + "enemies?type={s1}", String.class, enemyType);
        ObjectMapper mapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            EnemyResponse eResponse = mapper.readValue(response, EnemyResponse.class);
            return eResponse.getEnemies().stream()
                    .map(Enemy::getName)
                    .toList();
        } catch(Exception ex) {
            System.out.println("Something Happened!!");
            System.out.println(ex.getMessage());
            System.out.println(ex.getStackTrace());
        }

        return null;
    }

    public List<String> getObjectives() {

        return null;
    }

    public void savePlayer(Player player) {

        System.out.println("PLAYER: " + player.toString());
    }
}
