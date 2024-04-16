package com.helldivers.model;

import com.helldivers.entity.Stratagem;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class StratagemData{

    private Long stratagemId;
    private String name;
    private String description;
    private String code;
    private String category;
    private String type;
    private int uses;
    private int spawnTime;
    private int cooldown;
    private List<String> flags;

    public StratagemData(Stratagem stratagem) {

    }
}
