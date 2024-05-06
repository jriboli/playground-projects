package com.binaryNomad.caching.eviction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class CachingService {

    @Autowired
    private CacheManager cacheManager;

    //public CachingService(CacheManager cacheManager) {
    //    this.cacheManager = cacheManager;
    //}

    public void putToCache(String cacheName, String key, String value) {
        cacheManager.getCache(cacheName).put(key, value);
    }

    public String getFromCache(String cacheName, String key) {
        String value = null;
        if(cacheManager.getCache(cacheName).get(key) != null) {
            value = cacheManager.getCache(cacheName).get(key).get().toString();
        }

        return value;
    }

    @CacheEvict(value = "first", key = "#cacheKey")
    public void evictSingleCacheValue(String cacheKey) {

    }

    public void evictSingleCacheValue(String cacheName, String cacheKey) {
        cacheManager.getCache(cacheName).evict(cacheKey);
    }

    @CacheEvict(value = "first", allEntries = true)
    public void evictAllCacheValues() {

    }

    public void evictAllCacheValues(String cacheName) {
        cacheManager.getCache(cacheName).clear();
        Cache cacheInfo = cacheManager.getCache(cacheName);
        System.out.println(cacheInfo.toString());
    }

    public void evictAllCaches() {
        cacheManager.getCacheNames()
                .parallelStream()
                .forEach(cacheName -> cacheManager.getCache(cacheName).clear());
    }

    @Scheduled(fixedRate = 6000)
    public void evictAllCachesAtIntervals() {
        evictAllCaches();
    }
}
