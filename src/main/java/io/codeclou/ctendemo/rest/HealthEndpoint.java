package io.codeclou.ctendemo.rest;

import com.atlassian.plugins.rest.api.security.annotation.AdminOnly;

import io.codeclou.ctendemo.rest.model.OkModel;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
@Path("/health/")
public class HealthEndpoint {

    private static final Logger log = LoggerFactory.getLogger(HealthEndpoint.class);

    @GET
    @Path("/test")
    @AdminOnly
    public Response getAtlassianConfluenceLog() throws Exception {
        log.error("TEST_LOGGING");
        return Response.ok( "ok").build();
    }

}