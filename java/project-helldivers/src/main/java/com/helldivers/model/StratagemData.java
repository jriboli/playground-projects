package com.helldivers.model;

import com.helldivers.entity.Stratagem;
import com.helldivers.entity.StratagemFlag;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Comparator;
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
    private List<StratagemFlagData> flags;

    public StratagemData(Stratagem stratagem) {
        this.setStratagemId(stratagem.getStratagemId());
        this.setName(stratagem.getName());
        this.setDescription(stratagem.getDescription());
        this.setCode(stratagem.getCode());
        this.setCategory(stratagem.getCategory());
        this.setType(stratagem.getType());
        this.setUses(stratagem.getUses());
        this.setSpawnTime(stratagem.getSpawnTime());
        this.setCooldown(stratagem.getCooldown());

        flags = new ArrayList<>();
        List<StratagemFlag> sortedFlags = stratagem.getFlags().stream()
                .sorted(Comparator.comparing(StratagemFlag::getFlagId))
                .toList();

        for(StratagemFlag flag : sortedFlags) {
            flags.add(new StratagemFlagData(flag));
        }
    }
}
