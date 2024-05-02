package com.helldivers.populate.models.HelldiverAPI;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MatchData {
    private Long id;
    private String objective;
    private String location;
}
