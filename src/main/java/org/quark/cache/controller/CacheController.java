package org.quark.cache.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import org.quark.cache.model.TestDto;
import org.quark.cache.service.CacheService;

@Path("v1/cache/")
public class CacheController {

    @Inject
    CacheService cacheService;


    @POST
    @Path("save/{key}")
    public Response saveCache(@PathParam("key") String key, TestDto dto){
        return Response.ok(cacheService.insertTest(key,dto)).build();
    }

    @GET
    @Path("get/{key}")
    public Response getCache(@PathParam("key") String key){
        return Response.ok(cacheService.getTestDto(key)).build();
    }



}
