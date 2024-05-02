package com.helldivers.controller;

import com.helldivers.model.matches.*;
import com.helldivers.service.MatchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/matches")
@Slf4j
public class MatchesController {

    private MatchService service;

    public MatchesController (MatchService service) {
        this.service = service;
    }

    @GetMapping("/planets")
    public ResponseEntity<PlanetResponse> getPlanets(
            @RequestParam(required = false) Optional<String> planetType
    )
    {

        PlanetResponse response = new PlanetResponse();
        List<PlanetData> planets = new ArrayList<>();
        PlanetData planet1 = new PlanetData();
        planet1.setType("TERMINID");
        planet1.setName("HELLMIRE");

        PlanetData planet2 = new PlanetData();
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
            @RequestBody MatchData matchData
    ) {
        log.info("Creating Match!");
        return ResponseEntity.ok(service.saveMatch(matchData));
    }

    @PostMapping("/{matchId}/stats")
    public ResponseEntity<StatsResponse> addMatchStats(
            @PathVariable Long matchId,
            @RequestBody StatsData statsData
    ) {
        return ResponseEntity.ok(service.addStatsToMatch(matchId, statsData));
    }

    @PostMapping("/{matchId}/kills")
    public ResponseEntity<KillResponse> addKill(
            @PathVariable Long matchId,
            @RequestBody KillData killData
    ) {

        return ResponseEntity.ok(service.addKillToMatch(matchId, killData));
    }
}
