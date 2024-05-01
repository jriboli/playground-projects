package com.helldivers.controller;

import com.helldivers.entity.matches.Match;
import com.helldivers.model.Planet;
import com.helldivers.model.PlanetResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/match")
public class MatchesController {

    @GetMapping("/planets")
    public ResponseEntity<PlanetResponse> getPlanets(
            @RequestParam(required = false) Optional<String> planetType
    )
    {

        PlanetResponse response = new PlanetResponse();
        List<Planet> planets = new ArrayList<>();
        Planet planet1 = new Planet();
        planet1.setType("TERMINID");
        planet1.setName("HELLMIRE");

        Planet planet2 = new Planet();
        planet2.setType("ATUOMATON");
        planet2.setName("MENKENT");

        if(planetType.isPresent()) {
            if(planetType.get().equals("TERMINID")) {
                planets.add(planet1);
            }
            else {
                planets.add(planet2);
            }
        }
        else {
            planets.add(planet1);
            planets.add(planet2);
        }

        response.setPlanets(planets);

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<MatchResponse> addMatch(
            @RequestBody Match match
    ) {

    }
}
