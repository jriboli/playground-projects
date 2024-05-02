package com.binaryNomad.caching.eviction.controllers;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class CachingController {

    private CachingService cachingService;
    public CachingController(CachingService cachingService) {
        this.cachingService = cachingService;
    }
}
