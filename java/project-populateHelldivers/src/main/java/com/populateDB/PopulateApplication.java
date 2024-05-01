package com.populateDB;

import com.populateDB.factories.KillFactory;
import com.populateDB.factories.MatchFactory;
import com.populateDB.factories.PlayerFactory;
import com.populateDB.models.Kill;
import com.populateDB.models.Match;
import com.populateDB.models.Player;
import com.populateDB.service.HelldiverWrapperService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class PopulateApplication {
    private static HelldiverWrapperService service;
    private static final int DEFAULT_ENEMY_COUNT = 10;
    private static final double DEFAULT_DIFFICULTY_MULTIPLIER = 0.25;
    public static void main(String[] args) {
        SpringApplication.run(PopulateApplication.class, args);

        List<Player> players = populatePlayers(3);

        players.forEach(p -> p.setMatches(populateMatches(p, 2)));

        players.forEach(p -> p.getMatches()
                .forEach(m -> m.setKills(populateKills(p, m))));

        System.out.println("Players: " + players);
    }

    public static List<Player> populatePlayers(int numOfPlayers) {
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < numOfPlayers; i++) {
            Player player = PlayerFactory.createPlayer();
            players.add(player);
        }

        return players;
    }

    public static List<Match> populateMatches(Player player, int numOfMatches) {
        List<Match> matches = new ArrayList<>();
        for (int i = 0; i < numOfMatches; i++) {
            Match match = MatchFactory.createMatch(player);
            matches.add(match);
        }

        return matches;
    }

    public static List<Kill> populateKills(Player player, Match match) {
        int numOfKills = (int)(DEFAULT_ENEMY_COUNT * (DEFAULT_DIFFICULTY_MULTIPLIER * match.getDifficulty().value));
        List<Kill> kills = new ArrayList<>();
        for(int i = 0; i < numOfKills; i++) {
            Kill kill = KillFactory.createKill(player, match);
            kills.add(kill);
        }

        return kills;
    }
}
