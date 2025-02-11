package com.helldivers.populate.models;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WeaponFilters {
    private String trait;
    private String mode;
    private String type;
}
