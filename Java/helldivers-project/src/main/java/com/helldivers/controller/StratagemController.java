package com.helldivers.controller;

import com.helldivers.model.StratagemFlagData;
import com.helldivers.service.StratagemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import com.helldivers.model.StratagemData;

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
    public List<StratagemData> getAllStrategems(@RequestParam(required = false) Optional<String> category,
                                                @RequestParam(required = false) Optional<String> flag) {
        log.info("Calling Get All Stratagems.");

        //The parameter is wrapped in an Optional to handle the absence of the parameter more elegantly.
        if(category.isPresent()) {
            return stmService.getAllStratagemsByCategory(category.get());
        }

        if(flag.isPresent()) {
            return stmService.getAllStratagemsByFlag(flag.get());
        }

        return stmService.getAllStratagems();
    }

    @GetMapping("/stratagems/{stratagemId}")
    public StratagemData getStratagemById(@PathVariable Long stratagemId) {
        log.info("Calling Get Stratagem By Id.");
        return stmService.getStratagemById(stratagemId);
    }

    @PostMapping("/stratagems")
    public StratagemData addStrategem(@RequestBody StratagemData data) {
        log.info("Calling Create Stratagem.");
        return stmService.saveStratagem(data);
    }

    @PutMapping("/stratagems/{stratagemId}")
    public StratagemData updateStrategem(@PathVariable Long stratagemId, @RequestBody StratagemData data) {
        log.info("Calling Update Stratagem.");
        data.setStratagemId(stratagemId);
        return stmService.saveStratagem(data);
    }

    @DeleteMapping("/stratagems/{stratagemId}")
    public void removeStratagem(@PathVariable Long stratagemId) {
        log.info("Calling Delete Stratagem.");
        stmService.deleteStratagem(stratagemId);
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
}
