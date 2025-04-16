package io.codeclou.ctendemo.rest;

import com.atlassian.plugins.rest.api.security.annotation.AdminOnly;

import io.codeclou.ctendemo.rest.model.OkModel;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
@Path("/health/")
public class HealthEndpoint {

    @GET
    @Path("/test")
    @AdminOnly
    public Response getAtlassianConfluenceLog() throws Exception {
        return Response.ok(new OkModel()).build();
    }

}