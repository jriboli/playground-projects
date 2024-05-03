package com.binaryNomad.caching;

import com.binaryNomad.caching.eviction.service.CachingService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheFactoryBean;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

@ContextConfiguration
public class CacheEvictAnnotationIntegrationTest {

    @Configuration
    @EnableCaching
    static class ContextConfiguration {

        @Bean
        public CachingService cachingService() {
            return new CachingService(new ConcurrentMapCacheManager());
        }

        @Bean
        public CacheManager cacheManager() {
            SimpleCacheManager cacheManager = new SimpleCacheManager();
            List<Cache> caches = new ArrayList<>();
            caches.add(cacheBean().getObject());
            cacheManager.setCaches(caches);
            return cacheManager;
        }

        @Bean
        public ConcurrentMapCacheFactoryBean cacheBean() {
            ConcurrentMapCacheFactoryBean cacheFactoryBean = new ConcurrentMapCacheFactoryBean();
            cacheFactoryBean.setName("first");
            return cacheFactoryBean;
        }
    }

    CachingService cachingService;

    @Before
    public void setup() {
        cachingService.putToCache("first", "key1", "Baeldung");
        cachingService.putToCache("first", "key2", "Article");
    }

    @Test
    public void givenFirstCache_whenSingleCacheValueEvictRequested_thenEmptyCacheValue() {
        cachingService.evictSingleCacheValue("key1");
        String key1 = cachingService.getFromCache("first", "key1");
        assertThat(key1, is(nullValue()));
    }

    @Test
    public void givenFirstCahce_whenAllCahceValueEvictRequested_thenEmptyuache() {
        cachingService.evictAllCaches();
        String key1 = cachingService.getFromCache("first", "key1");
        String key2 = cachingService.getFromCache("first", "key2");
        assertThat(key1, is(nullValue()));
        assertThat(key2, is(nullValue()));

    }
}
