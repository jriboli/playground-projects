package com.populateDB.models.HelldiverAPI;

import lombok.Data;

import java.util.List;

@Data
public class PlanetResponse {
    private List<Planet> planets;
}
