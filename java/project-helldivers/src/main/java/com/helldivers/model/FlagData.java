package com.helldivers.model;

import com.helldivers.entity.stratagems.Flag;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FlagData {
    private Long flagId;
    private String name;

    public FlagData(Flag data) {
        this.setFlagId(data.getFlagId());
        this.setName(data.getName());
    }
}
