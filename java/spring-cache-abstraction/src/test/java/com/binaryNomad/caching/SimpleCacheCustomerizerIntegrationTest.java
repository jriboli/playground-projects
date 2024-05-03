package com.binaryNomad.caching;

import org.junit.Test;
import org.springframework.cache.CacheManager;

import static org.assertj.core.api.Assertions.assertThat;
public class SimpleCacheCustomerizerIntegrationTest {

    private CacheManager cacheManager;

    public SimpleCacheCustomerizerIntegrationTest(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    @Test
    public void givenCacheManagerCustomerizerWhenBootstrappedThenCacheManagerCustomized() {
        assertThat(cacheManager.getCacheNames())
                .containsOnly(SimpleCacheCustomizer.USERS_CACHE, SimpleCacheCustomizer.TRANSACTIONS_CACHE);
    }
}
