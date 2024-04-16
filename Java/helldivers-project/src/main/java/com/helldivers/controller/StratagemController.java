package com.helldivers.controller;

import com.helldivers.service.StratagemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import com.helldivers.model.StratagemData;

import java.util.List;

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
    public List<StratagemData> getAllStrategems() {
        return stmService.getAllStratagems();
    }

    @GetMapping("/stratagems/{stratagemId}")
    public StratagemData getStratagemById(@PathVariable Long stratagemId) {
        return stmService.getStratagemById(stratagemId);
    }

    @PostMapping("/stratagems")
    public void addStrategem(@RequestBody StratagemData data) {
        stmService.createStratagem(data);
    }

    @PutMapping("/stratagems/{stratagemId}")
    public void updateStrategem(@PathVariable Long stratagemId, @RequestBody StratagemData data) {
        stmService.updateStratagem(stratagemId, data);
    }

    @DeleteMapping("/stratagems/{stratagemId}")
    public void removeStratagem(@PathVariable Long stratagemId) {
        stmService.removeStratagem(stratagemId);
    }

    // STRATAGEM FLAGS API
    @GetMapping("/flags")
    public List<String> getAllFlags() {
        List<String> results = stmService.getAllFlags();
        return null;
    }

    @GetMapping("/flags/{flagId}")
    public String getFlagById(@PathVariable Long flagId) {
        String result = stmService.getFlagById(flagId);
        return null;
    }
}
