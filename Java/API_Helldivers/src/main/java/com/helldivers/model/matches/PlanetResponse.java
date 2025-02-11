package com.helldivers.model.matches;

import lombok.Data;

import java.util.List;

@Data
public class PlanetResponse {
    private List<PlanetData> planets;
}
