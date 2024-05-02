package com.helldivers.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class PlayerResponse {
    private Integer num_of_players;
    private List<PlayerData> players;

    public PlayerResponse(List<PlayerData> data) {
        this.players = data;
        num_of_players = data.size();
    }

    public PlayerResponse(PlayerData data) {
        this.players = List.of(data);
        num_of_players = 1;
    }
}
