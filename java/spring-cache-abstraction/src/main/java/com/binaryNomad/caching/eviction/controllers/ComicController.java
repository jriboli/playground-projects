package com.binaryNomad.caching.eviction.controllers;

import com.binaryNomad.caching.eviction.service.ComicService;
import com.binaryNomad.caching.model.Comic;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comics")
public class ComicController {
    private ComicService comicService;

    public ComicController(ComicService comicService) {
        this.comicService = comicService;
    }

    @GetMapping
    public ResponseEntity<List<Comic>> getComics() {
        return ResponseEntity.ok(comicService.getComicList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comic> getComicById(
            @PathVariable int id
    ) {
        return ResponseEntity.ok(comicService.getComicById(id));
    }

    @GetMapping("/{id}/condition")
    public ResponseEntity<Comic> getComicByIdWithCondition(
            @PathVariable int id
    ) {
        return ResponseEntity.ok(comicService.getComicByIdWithCondition(id));
    }

    @GetMapping("/{id}/unless")
    public ResponseEntity<Comic> getComicByIdWithUnless(
            @PathVariable int id
    ) {
        return ResponseEntity.ok(comicService.getComicByIdWithUnless(id));
    }

    @GetMapping("/cache")
    public ResponseEntity<String> examineCache() {
        comicService.showCache();
        return ResponseEntity.ok("Got 'em");
    }
}
