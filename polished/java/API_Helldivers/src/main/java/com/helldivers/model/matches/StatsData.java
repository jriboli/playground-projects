package com.helldivers.model.matches;

import com.helldivers.entity.matches.Stats;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StatsData {
    private Long id;
    private Long player_id;
    private String weapon_name;
    private Integer shots_fired;
    private Integer shots_hit;

    public StatsData(Stats stats) {
        this.id = stats.getId();
        this.player_id = stats.getPlayer().getId();
        this.weapon_name = stats.getWeapon().getName();
        this.shots_fired = stats.getShotsFired();
        this.shots_hit = stats.getShotsHit();
    }
}
