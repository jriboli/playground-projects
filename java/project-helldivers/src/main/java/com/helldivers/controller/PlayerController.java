package com.helldivers.controller;

import com.helldivers.enums.player.Type;
import com.helldivers.model.PlayerData;
import com.helldivers.model.PlayerResponse;
import com.helldivers.service.PlayerService;
import org.apache.coyote.Request;
import org.apache.coyote.Response;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/players")
public class PlayerController {

    private PlayerService service;

    public PlayerController(PlayerService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<PlayerResponse> getPlayers(
            @RequestParam(required = false) Optional<String> type
    ) {

        PlayerResponse response;
        if(type.isPresent()) {
            Type tType = type.isPresent() ? Type.valueOf(type.get()) : null;
            response = service.getPlayersWithFilter(tType);
        } else {
            response = service.getPlayers();
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{playerId}")
    public ResponseEntity<PlayerResponse> getPlayerById(@PathVariable Long playerId) {

        return ResponseEntity.ok(service.getPlayerById(playerId));
    }

    @PostMapping
    public ResponseEntity<PlayerResponse> createPlayer(@RequestBody PlayerData playerData) {

        return ResponseEntity.ok(service.savePlayer(playerData));
    }

    @DeleteMapping("/{playerId}")
    public ResponseEntity<String> deletePlayer(@PathVariable Long playerId) {

        // TBD
        return null;
    }
}
