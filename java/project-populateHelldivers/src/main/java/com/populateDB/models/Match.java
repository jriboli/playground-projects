package com.populateDB.models;

import com.populateDB.enums.Difficulty;
import lombok.Data;

import java.util.List;

@Data
public class Match {
    private String planet;
    private String objective;
    private Integer shotsFired;
    private Integer shotsHit;
    private String weapons;
    private Difficulty difficulty;

    private List<Kill> kills;
}
