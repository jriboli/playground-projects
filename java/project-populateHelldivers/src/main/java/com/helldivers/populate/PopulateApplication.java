package com.helldivers.populate;

import com.helldivers.populate.factories.KillFactory;
import com.helldivers.populate.factories.MatchFactory;
import com.helldivers.populate.factories.PlayerFactory;
import com.helldivers.populate.models.Kill;
import com.helldivers.populate.models.Match;
import com.helldivers.populate.models.Player;
import com.helldivers.populate.service.HelldiverWrapperService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class PopulateApplication {
    private static HelldiverWrapperService service = new HelldiverWrapperService();
    private static final int NUM_OF_PLAYERS = 50;
    private static final int NUM_OF_MATCHES = 50;
    private static final int DEFAULT_ENEMY_COUNT = 100;
    private static final double DEFAULT_DIFFICULTY_MULTIPLIER = 0.25;
    public static void main(String[] args) {
        SpringApplication.run(PopulateApplication.class, args);

        List<Player> players = populatePlayers(NUM_OF_PLAYERS);

        players.forEach(p -> p.setMatches(populateMatches(p, NUM_OF_MATCHES)));

        players.forEach(p -> p.getMatches()
                .forEach(m -> m.setKills(populateKills(p, m))));

        System.out.println("Players: " + players);

        service.save(players);
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
