package com.populateDB.models;

import com.populateDB.enums.Difficulty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
public class Match {
    private String planet;
    private String objective;
    private Integer shotsFired;
    private Integer shotsHit;
    private String weapons;
    private Difficulty difficulty;

    private String enemyType;

    private List<Kill> kills;
}
