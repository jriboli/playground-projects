package com.helldivers.model;

import com.helldivers.entity.StratagemFlag;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StratagemFlagData {
    private Long flagId;
    private String name;

    public StratagemFlagData(StratagemFlag data) {
        this.setFlagId(data.getFlagId());
        this.setName(data.getName());
    }
}
