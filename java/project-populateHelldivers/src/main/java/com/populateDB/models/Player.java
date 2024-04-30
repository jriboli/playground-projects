package com.populateDB.models;

import com.populateDB.enums.PlayerType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Player {
    private String name;
    private String email;
    private String gamerTag;
    private PlayerType type;

    private List<Match> matches;
}
