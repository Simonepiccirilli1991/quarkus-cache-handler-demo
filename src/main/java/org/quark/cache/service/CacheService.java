package org.quark.cache.service;

import io.quarkus.cache.*;
import jakarta.enterprise.context.ApplicationScoped;
import org.quark.cache.model.TestDto;

import java.util.concurrent.CompletableFuture;

@ApplicationScoped
public class CacheService {

    @CacheName("cachy")
    Cache cacheTest;

    @CacheName("finalCache")
    Cache cacheFinal;

    public String insertTest(String key, TestDto value){
        try {
            cacheTest.as(CaffeineCache.class).put(key, CompletableFuture.completedFuture(value));
            return "ok";
        }catch (Exception e){
            throw new RuntimeException();
        }
    }

    public CompletableFuture<Object> getTestDto(String key){
        return cacheTest.as(CaffeineCache.class).getIfPresent(key);
    }

    public String insertFinalTest(String key, TestDto value){
        try {
            cacheFinal.as(CaffeineCache.class).put(key, CompletableFuture.completedFuture(value));
            return "ok";
        }catch (Exception e){
            throw new RuntimeException();
        }
    }

    public CompletableFuture<Object> getFinalTestDto(String key){
        return cacheFinal.as(CaffeineCache.class).getIfPresent(key);
    }
}
