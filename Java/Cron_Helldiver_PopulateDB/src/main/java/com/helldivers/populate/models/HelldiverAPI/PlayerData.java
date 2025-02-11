package com.helldivers.populate.models.HelldiverAPI;

import com.helldivers.populate.enums.PlayerType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PlayerData {
    private Long id;
    private String name;
    private String email;
    private String gamerTag;
    private PlayerType type;
}
