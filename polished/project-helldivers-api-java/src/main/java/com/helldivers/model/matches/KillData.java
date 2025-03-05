package com.helldivers.model.matches;

import com.helldivers.entity.matches.Kill;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class KillData {
    private Long id;
    private Long player_id;
    private Integer time_of_kill;
    private String enemy_name;
    private String weapon_name;

    public KillData(Kill kill) {
        this.id = kill.getId();
        this.player_id = kill.getPlayer().getId();
        this.time_of_kill = kill.getTimeOfKill();
        this.enemy_name = kill.getEnemy().getName();
        this.weapon_name = kill.getWeapon().getName();
    }
}
