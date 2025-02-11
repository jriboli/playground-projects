package com.helldivers.model.matches;

import com.helldivers.entity.matches.Kill;
import lombok.Data;

@Data
public class KillResponse {
    private KillData killData;

    public KillResponse(KillData data){
        this.killData = data;
    }
}
