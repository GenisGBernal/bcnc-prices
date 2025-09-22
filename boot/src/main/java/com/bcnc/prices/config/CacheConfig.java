/*
 * Copyright (c) 2025 BCNC.
 * All rights reserved.
 */
package com.bcnc.prices.config;

import com.bcnc.prices.application.config.BaseCacheConfig;
import com.github.benmanes.caffeine.cache.Caffeine;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CacheConfig {

  @Bean
  public CacheManager cacheManager(Collection<? extends BaseCacheConfig> cacheConfigs) {
    SimpleCacheManager cacheManager = new SimpleCacheManager();

    List<CaffeineCache> caches =
        cacheConfigs.stream()
            .map(
                cacheConfig -> {
                  if (cacheConfig.getRefreshAfterWrite() != null
                      && cacheConfig.getExpireAfterWriteSeconds() != null) {
                    throw new IllegalArgumentException(
                        "Cannot use refreshAfterWrite and expireAfterWrite together for cache: "
                            + cacheConfig.getName());
                  } else if (cacheConfig.getMaximumSize() <= 0) {
                    throw new IllegalArgumentException(
                        "Maximum cache size is below or equal zero for cache: "
                            + cacheConfig.getName());
                  }

                  Caffeine<Object, Object> cacheBuilder =
                      Caffeine.newBuilder()
                          .initialCapacity(cacheConfig.getInitialSize())
                          .maximumSize(cacheConfig.getMaximumSize())
                          .recordStats();

                  if (cacheConfig.getExpireAfterWriteSeconds() != null)
                    cacheBuilder.expireAfterWrite(
                        cacheConfig.getExpireAfterWriteSeconds(), TimeUnit.SECONDS);
                  if (cacheConfig.getExpireAfterAccessSeconds() != null)
                    cacheBuilder.expireAfterAccess(
                        cacheConfig.getExpireAfterAccessSeconds(), TimeUnit.SECONDS);
                  if (cacheConfig.getRefreshAfterWrite() != null)
                    cacheBuilder.refreshAfterWrite(
                        cacheConfig.getRefreshAfterWrite(), TimeUnit.SECONDS);

                  return new CaffeineCache(cacheConfig.getName(), cacheBuilder.build());
                })
            .toList();

    cacheManager.setCaches(caches);
    return cacheManager;
  }
}
