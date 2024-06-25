package com.evaloper.SolidTrust.infrastructure.config.cacheConfig;


import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    public CacheManager cacheManager(){
        CaffeineCacheManager cacheManager = new CaffeineCacheManager("transactionsCache", "profilePicsCache");
        cacheManager.setCaffeine(caffeineCacheBuilder());

        cacheManager.setCaffeine(profilePicsCacheBuilder());

        return cacheManager;
    }

    Caffeine<Object, Object> caffeineCacheBuilder() {
        return Caffeine.newBuilder()
                .initialCapacity(100)
                .maximumSize(1000)
                .expireAfterWrite(Duration.ofMinutes(10));
    }

    Caffeine <Object, Object> profilePicsCacheBuilder() {
        return Caffeine.newBuilder()
                .initialCapacity(100)
                .maximumSize(100)
                .expireAfterWrite(Duration.ofHours(24));
    }
}
