package org.quark.cache.service;

import io.quarkus.cache.Cache;
import io.quarkus.cache.CacheName;
import io.quarkus.cache.CaffeineCache;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import org.quark.cache.model.SessionDto;
import org.quark.cache.model.SessionRequest;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@ApplicationScoped
@Slf4j
public class SessionCacheService {


    @CacheName("cachy")
    Cache sessionCache;


    public Response createSession(SessionRequest request){

        log.info("CreateSession service start with request: {}",request);

        var sessionId = UUID.randomUUID();

        var session = new SessionDto();
        session.setEmail(request.getEmail());
        session.setScope(request.getScope());
        session.setIsValid(true);
        session.setSessionStart(LocalDateTime.now());

        sessionCache.as(CaffeineCache.class).put(sessionId, CompletableFuture.completedFuture(session));

        return Response.ok().header("iSession",sessionId).build();
    }

    public CompletableFuture<Object> getSession(String sessionId){
        return sessionCache.as(CaffeineCache.class).getIfPresent(sessionId);
    }
}
