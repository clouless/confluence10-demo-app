package io.codeclou.ctendemo.rest;

import com.atlassian.confluence.setup.settings.GlobalSettingsManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/download/")
public class DownloadEndpoint {

    private static final Logger log = LoggerFactory.getLogger(DownloadEndpoint.class);

    // Confluence
    private GlobalSettingsManager globalSettingsManager;


    @Inject
    DownloadEndpoint(
                     GlobalSettingsManager globalSettingsManager) {
        this.globalSettingsManager = globalSettingsManager;
    }

    @GET
    @Path("/json")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response jsonList(
        @QueryParam("contentId") Long contentId,
        @QueryParam("macroId") String macroId_raw
    ) {
        return Response.ok("foo").build();
    }

}
