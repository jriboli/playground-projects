package com.helldivers.model.matches;

import com.helldivers.entity.matches.Match;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MatchData {
    private Long id;
    private String objective;
    private String location;

    public MatchData(Match match) {
        this.id = match.getId();
        this.objective = match.getObjective().name();
        this.location = match.getLocation().name();
    }
}
