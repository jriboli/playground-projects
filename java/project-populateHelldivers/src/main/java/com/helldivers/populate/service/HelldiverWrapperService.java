package com.helldivers.populate.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.helldivers.populate.models.HelldiverAPI.*;
import com.helldivers.populate.models.Kill;
import com.helldivers.populate.models.Match;
import com.helldivers.populate.models.Player;
import com.helldivers.populate.models.WeaponFilters;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class HelldiverWrapperService {

    private final String apiBaseUrl = "http://localhost:9000/api/v1/";
    private final ObjectMapper mapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    private HttpHeaders headers = new HttpHeaders();

    public HelldiverWrapperService() {
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    public List<Weapon> getWeapons() {
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(apiBaseUrl + "weapons", String.class);
        try {
            WeaponResponse wResponse = mapper.readValue(response, WeaponResponse.class);
            return wResponse.getWeapons();

        } catch(Exception ex) {
            System.out.println("Something Happened!!");
            System.out.println(ex.getMessage());
            System.out.println(ex.getStackTrace());
        }

        return null;
    }

    public List<Planet> getPlanets() {
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(apiBaseUrl + "matches/planets", String.class);
        try {
            PlanetResponse pResponse = mapper.readValue(response, PlanetResponse.class);
            return pResponse.getPlanets();
        } catch(Exception ex) {
            System.out.println("Something Happened!!");
            System.out.println(ex.getMessage());
            System.out.println(ex.getStackTrace());
        }

        return null;
    }

    public List<Enemy> getEnemies() {
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(apiBaseUrl + "enemies?", String.class);
        try {
            EnemyResponse eResponse = mapper.readValue(response, EnemyResponse.class);
            return eResponse.getEnemies();
        } catch(Exception ex) {
            System.out.println("Something Happened!!");
            System.out.println(ex.getMessage());
            System.out.println(ex.getStackTrace());
        }

        return null;
    }

    public List<String> getObjectives() {

        // TBD
        return null;
    }

    public void save(List<Player> records) {
        records.forEach(this::saveRecord);
    }

    public void saveRecord(Player player) {

        // Player
        player.setId(createPlayer(player));

        // Matches
        List<Match> matches = player.getMatches();
        matches.forEach(m -> m.setId(saveMatchAndStats(player.getId(), m)));

        // Kills
        for(Match match : matches) {
            List<Kill> kills = match.getKills();
            kills.forEach(k -> saveKill(player.getId(), match.getId(), k));
        }

        System.out.println("PLAYER: " + player.toString());
    }

    private Long createPlayer(Player playerInfo) {
        PlayerData playerData = new PlayerData();
        playerData.setName(playerInfo.getName());
        playerData.setEmail(playerInfo.getEmail());
        playerData.setGamerTag(playerInfo.getGamerTag());
        playerData.setType(playerInfo.getType());

        try {
            String jsonData = mapper.writeValueAsString(playerData);
            HttpEntity<String> request = new HttpEntity<>(jsonData, headers);
            RestTemplate restTemplate = new RestTemplate();
            String response = restTemplate.postForObject(apiBaseUrl + "players", request, String.class);
            PlayerResponse pResponse = mapper.readValue(response, PlayerResponse.class);
            return pResponse.getPlayers().get(0).getId();

        } catch(Exception ex) {
            System.out.println("Something Happened!!");
            System.out.println(ex.getMessage());
            System.out.println(ex.getStackTrace());
        }

        return null;
    }

    private Long saveMatchAndStats(Long playerId, Match matchInfo) {
        Long matchId = saveMatch(matchInfo);
        matchInfo.setId(matchId);
        saveStats(playerId, matchInfo);

        return matchId;
    }

    private Long saveMatch(Match matchInfo) {
        MatchData matchData = new MatchData();
        matchData.setObjective(matchInfo.getObjective());
        matchData.setLocation(matchInfo.getLocation());

        try {
            String jsonData = mapper.writeValueAsString(matchData);
            HttpEntity<String> request = new HttpEntity<>(jsonData, headers);
            RestTemplate restTemplate = new RestTemplate();
            String response = restTemplate.postForObject(apiBaseUrl + "matches", request, String.class);
            MatchResponse mResponse = mapper.readValue(response, MatchResponse.class);
            return mResponse.getMatch().getId();

        } catch(Exception ex) {
            System.out.println("Something Happened!!");
            System.out.println(ex.getMessage());
            System.out.println(ex.getStackTrace());
        }

        return null;
    }

    private void saveStats(Long playerId, Match matchInfo) {
        StatData statData = new StatData();
        statData.setShots_fired(matchInfo.getShotsFired());
        statData.setShots_hit(matchInfo.getShotsHit());
        statData.setPlayer_id(playerId);
        statData.setWeapon_name(matchInfo.getWeapons());

        try {
            String jsonData = mapper.writeValueAsString(statData);
            HttpEntity<String> request = new HttpEntity<>(jsonData, headers);
            RestTemplate restTemplate = new RestTemplate();
            String response = restTemplate.postForObject(apiBaseUrl + "matches/{matchId}/stats", request, String.class, matchInfo.getId());
            StatResponse sResponse = mapper.readValue(response, StatResponse.class);

        } catch(Exception ex) {
            System.out.println("Something Happened!!");
            System.out.println(ex.getMessage());
            System.out.println(ex.getStackTrace());
        }
    }

    private void saveKill(Long playerId, Long matchId, Kill killInfo) {
        KillData killData = new KillData();
        killData.setPlayer_id(playerId);
        killData.setEnemy_name(killInfo.getEnemy());
        killData.setWeapon_name(killInfo.getWeapon());
        killData.setTime_of_kill(Integer.valueOf(killInfo.getTimeOfKill()));

        try {
            String jsonData = mapper.writeValueAsString(killData);
            HttpEntity<String> request = new HttpEntity<>(jsonData, headers);
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.postForEntity(apiBaseUrl + "matches/{matchId}/kills", request, String.class, matchId);
            JsonNode root = mapper.readTree(response.getBody());
            System.out.println("RESPONSE: " + root);

        } catch(Exception ex) {
            System.out.println("Something Happened!!");
            System.out.println(ex.getMessage());
            System.out.println(ex.getStackTrace());
        }
    }
}
