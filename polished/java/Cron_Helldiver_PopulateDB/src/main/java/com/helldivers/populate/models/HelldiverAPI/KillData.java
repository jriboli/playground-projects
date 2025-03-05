package com.helldivers.populate.models.HelldiverAPI;

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
}
