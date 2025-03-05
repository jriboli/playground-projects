package com.helldivers.entity.matches;

import com.helldivers.entity.enemies.Enemy;
import com.helldivers.entity.players.Player;
import com.helldivers.entity.weapons.Weapon;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "kill_records")
public class Kill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "match_id")
    private Match match;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;

    private Integer timeOfKill;

    @ManyToOne
    @JoinColumn(name = "enemy_id")
    private Enemy enemy;

    @ManyToOne
    @JoinColumn(name = "weapon_id")
    private Weapon weapon;
}
