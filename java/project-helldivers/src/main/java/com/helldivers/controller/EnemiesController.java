package com.helldivers.controller;

import com.helldivers.enums.enemies.Type;
import com.helldivers.model.enemies.EnemyResponse;
import com.helldivers.service.EnemyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/enemies")
@Slf4j
public class EnemiesController {

    private EnemyService service;

    public EnemiesController(EnemyService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<EnemyResponse> getEnemies(
            @RequestParam(required = false) Optional<String> type
    ){
        log.info("Calling Get All Enemies.");
        EnemyResponse response;

        if(type.isPresent()) {
            Type tType = type.isPresent() ? Type.valueOf(type.get()) : null;
            response = service.getEnemiesWithFilter(tType);
        } else {
            response = service.getEnemies();
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{enemyId}")
    public ResponseEntity<EnemyResponse> getEnemyById(@PathVariable Long enemyId) {

        return ResponseEntity.ok(service.getEnemyById(enemyId));
    }

}
