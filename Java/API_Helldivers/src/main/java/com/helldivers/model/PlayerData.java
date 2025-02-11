package com.helldivers.model;

import com.helldivers.entity.players.Player;
import com.helldivers.enums.player.Type;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PlayerData {
    private Long id;
    private String name;
    private String email;
    private String gamerTag;
    private Type type;

    public PlayerData(Player player) {
        this.id = player.getId();
        this.name = player.getName();
        this.email = player.getEmail();
        this.gamerTag = player.getGamerTag();
        this.type = player.getType();
    }
}
