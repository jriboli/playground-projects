package com.helldivers.model.matches;

import lombok.Data;

@Data
public class MatchResponse {

    private MatchData match;

    public MatchResponse(MatchData data){
        this.match = data;
    }
}
