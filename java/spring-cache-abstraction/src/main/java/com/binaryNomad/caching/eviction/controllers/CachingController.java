package com.binaryNomad.caching.eviction.controllers;

import com.binaryNomad.caching.eviction.service.CachingService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CachingController {

    private CachingService cachingService;
    public CachingController(CachingService cachingService) {
        this.cachingService = cachingService;
    }
}
