package com.binaryNomad.message_queue.server.controller;

import com.binaryNomad.message_queue.server.model.GameEventMessage;
import com.binaryNomad.message_queue.server.service.GameEventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/event")
public class GameEventController {

    private GameEventService gameEventService;

    public GameEventController(GameEventService gameEventService) {
        this.gameEventService = gameEventService;
    }

    @PostMapping
    public ResponseEntity addGameEvent(
            @RequestBody GameEventMessage message
            ) {

        // Business Logic
        gameEventService.addGameEvent(message);

        return new ResponseEntity<>(Map.of("message", "Event added."), HttpStatus.OK);
    }
}
