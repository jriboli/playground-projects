package com.helldivers.populate.models;

import com.helldivers.populate.enums.Difficulty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Match {
    private Long id;
    private String location;
    private String objective;
    private Integer shotsFired;
    private Integer shotsHit;
    private String weapons;
    private Difficulty difficulty;

    private String enemyType;

    private List<Kill> kills;
}
