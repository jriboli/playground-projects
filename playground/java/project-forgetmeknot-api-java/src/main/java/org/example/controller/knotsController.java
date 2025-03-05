package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.model.KnotsData;
import org.example.service.knotsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
public class knotsController {

    private knotsService service;

    public knotsController(knotsService service) { this.service = service; }
    @GetMapping("/knots")
    public List<KnotsData> getAllPublicKnots() {
        log.info("Grabbing public knots");
        return service.getAllPublicKnots();

    }

    @GetMapping("/knots/{knotId}")
    public KnotsData getPublicKnotById(@PathVariable Long knotId){
        log.info("Grabbing knot info for Id = " + knotId);
        return service.getPublicKnotById(knotId);
    }
}
