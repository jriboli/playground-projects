package com.populateDB.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Kill {
    private String enemy;
    private String weapon;
    private String timeOfKill;
}
