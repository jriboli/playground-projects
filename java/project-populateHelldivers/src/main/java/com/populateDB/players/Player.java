package com.populateDB.players;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Player {
    private String name;
    private String email;
    private String gamerTag;
    private PlayerType type;
}
