package com.binaryNomad.caching.eviction.service;

import com.binaryNomad.caching.model.Comic;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

// https://stackoverflow.com/questions/16899604/spring-cache-cacheable-not-working-while-calling-from-another-method-of-the-s
// https://medium.com/upday-devs/3-common-mistakes-when-implementing-spring-cache-abstraction-a7ac2ee247ba

@Component
@Slf4j
//@CacheConfig(cacheNames = "comic")
public class ComicService {

    private List<Comic> comicList = new ArrayList<>();
    private CacheManager cacheManager;
    public ComicService(CacheManager cacheManager) {
        this.cacheManager = cacheManager;

        comicList.add(new Comic(1, "Superman", "Lex Luther"));
        comicList.add(new Comic(2, "Spiderman", "Doc Oc"));
    }

    //@Cacheable(sync=true)
    @Cacheable(cacheNames="comics", key="1")
    public List<Comic> getComicList() {
        log.info("Retrieving all comics from data source.");
        return comicList;
    }

    //@Cacheable(sync=true)
    @Cacheable(cacheNames="comic", key="#id")
    public Comic getComicById(int id) {
        log.info("Comic By Id - [" + id + "]");
//        return comics.get(id);
        return comicList.stream()
                .filter(b -> b.getId() == id)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("No comic found with that ID"));
    }

    @CachePut(cacheNames="comic", condition="#id==1")
    public Comic getComicByIdWithCondition(int id) {
        log.info("Comic By Id - [" + id + "]");
        return comicList.stream()
                .filter(b -> b.getId() == id)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("No comic found with that ID"));
    }

    @CachePut(cacheNames="comic", unless="#id==2")
    public Comic getComicByIdWithUnless(int id) {
        log.info("Comic By Id - [" + id + "]");
        return comicList.stream()
                .filter(b -> b.getId() == id)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("No comic found with that ID"));
    }

    public void showCache() {
        cacheManager.getCacheNames().forEach(c -> System.out.println("Cache Name: " + c));

        Cache cache = cacheManager.getCache("comic");
        for (int i = 0; i < 10; i++) {
            System.out.println("Cache Value[" + i + "]: " +cache.get(i));
        }

        Cache cache2 = cacheManager.getCache("comics");
        for (int i = 0; i < 10; i++) {
            System.out.println("Cache Value[" + i + "]: " +cache2.get(i));
        }
    }




}
