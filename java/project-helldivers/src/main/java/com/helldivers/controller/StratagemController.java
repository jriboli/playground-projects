package com.helldivers.controller;

import com.helldivers.model.StratagemFlagData;
import com.helldivers.model.StratagemResponse;
import com.helldivers.service.StratagemService;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.helldivers.model.StratagemData;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@Slf4j
public class StratagemController {

    private StratagemService stmService;

    public StratagemController(StratagemService stmService) {
        this.stmService = stmService;
    }

    // STRATAGEMS API
    @GetMapping("/stratagems")
    public ResponseEntity<StratagemResponse> getAllStrategems(
            @RequestParam(required = false) Optional<String> category,
            @RequestParam(required = false) Optional<String> flag) {

        log.info("Calling Get All Stratagems.");
        StratagemResponse response;

        //The parameter is wrapped in an Optional to handle the absence of the parameter more elegantly.
        if(category.isPresent()) {
            response = new StratagemResponse(stmService.getAllStratagemsByCategory(category.get()));
        } else if(flag.isPresent()) {
            response = new StratagemResponse(stmService.getAllStratagemsByFlag(flag.get()));
        } else {
            response = new StratagemResponse(stmService.getAllStratagems());
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping("/stratagems/{stratagemId}")
    public ResponseEntity<StratagemResponse> getStratagemById(@PathVariable Long stratagemId) {
        log.info("Calling Get Stratagem By Id.");
        StratagemResponse response = new StratagemResponse(stmService.getStratagemById(stratagemId));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/stratagems")
    public ResponseEntity<StratagemData> addStrategem(@RequestBody StratagemData data) {
        log.info("Calling Create Stratagem.");

        StratagemData response = stmService.saveStratagem(data);
        URI location = URI.create("/api/v1/stratagems/" + response.getStratagemId());
        return ResponseEntity.created(location).body(response);
    }

    @PutMapping("/stratagems/{stratagemId}")
    public ResponseEntity<StratagemData> updateStrategem(@PathVariable Long stratagemId, @RequestBody StratagemData data) {
        log.info("Calling Update Stratagem.");
        data.setStratagemId(stratagemId);

        StratagemData response = stmService.saveStratagem(data);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/stratagems/{stratagemId}")
    public ResponseEntity<String> removeStratagem(@PathVariable Long stratagemId) {
        log.info("Calling Delete Stratagem.");
        stmService.deleteStratagem(stratagemId);

        return ResponseEntity.ok("Strategem removed.");
    }

    // STRATAGEM FLAGS API
    @GetMapping("/flags")
    public List<StratagemFlagData> getAllFlags() {
        log.info("Calling Get All Flags.");
        return stmService.getAllFlags();
    }

    @GetMapping("/flags/{flagId}")
    public StratagemFlagData getFlagById(@PathVariable Long flagId) {
        log.info("Calling Get Flag By Id.");
        return stmService.getFlagById(flagId);
    }

    @PostMapping("/flags")
    public StratagemFlagData addFlag(@RequestBody StratagemFlagData data) {
        log.info("Calling Add Flag");
        return stmService.saveFlag(data);
    }
}
