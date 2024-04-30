package com.populateDB.players;

public class PlayerPopulator {
    private final PlayerService playerService;

    public PlayerPopulator(PlayerService playerService) {
        this.playerService = playerService;
    }

    public void populatePlayers(int numPlayers) {
        for (int i = 0; i < numPlayers; i++) {
            Player player = PlayerFactory.createPlayer();
            playerService.savePlayer(player);
        }
    }
}
