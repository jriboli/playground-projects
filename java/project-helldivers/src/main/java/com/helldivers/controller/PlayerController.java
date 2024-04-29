package com.helldivers.controller;

import com.helldivers.model.PlayerData;
import com.helldivers.model.PlayerResponse;
import org.apache.coyote.Request;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/players")
public class PlayerController {

    @GetMapping
    public ResponseEntity<PlayerResponse> getPlayers() {

        return null;
    }

    @GetMapping("/{playerId}")
    public ResponseEntity<PlayerResponse> getPlayerById(@PathVariable Long playerId) {

        return ResponseEntity.ok(null);
    }

    @PostMapping
    public ResponseEntity<PlayerResponse> createPlayer(@RequestBody PlayerData playerData) {

        return null;
    }

    @PutMapping("/{playerId}")
    public ResponseEntity<PlayerResponse> updatePlayer(@RequestBody PlayerData playerData, @PathVariable Long playerId) {

        return null;
    }

    @DeleteMapping("/{playerId}")
    public ResponseEntity<String> deletePlayer(@PathVariable Long playerId) {

        return null;
    }
}
