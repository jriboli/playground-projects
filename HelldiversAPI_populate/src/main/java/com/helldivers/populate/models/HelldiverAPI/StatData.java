package com.helldivers.populate.models.HelldiverAPI;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StatData {
    private Long id;
    private Long player_id;
    private String weapon_name;
    private Integer shots_fired;
    private Integer shots_hit;
}
