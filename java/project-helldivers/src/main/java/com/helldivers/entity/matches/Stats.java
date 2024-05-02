package com.helldivers.entity.matches;

import com.helldivers.entity.players.Player;
import com.helldivers.entity.weapons.Weapon;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Stats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "match_id")
    private Match match;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;

    @ManyToOne
    @JoinColumn(name = "weapon_id")
    private Weapon weapon;

    private Integer shotsFired;
    private Integer shotsHit;
}
