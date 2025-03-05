package com.helldivers.model.matches;

import com.helldivers.entity.matches.Stats;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class StatsResponse {
    private List<StatsData> stats;

    public StatsResponse(List<StatsData> data) {
        stats = data;
    }

    public StatsResponse(StatsData data) {
        stats = List.of(data);
    }
}
