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

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

@Path("/httpclient/")
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
    @Path("/download")
    @Produces({MediaType.TEXT_PLAIN})
    public Response download() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://raw.githubusercontent.com/clouless/confluence10-demo-app/refs/heads/main/LICENSE"))
                .build();
        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .followRedirects(HttpClient.Redirect.NEVER)
                .connectTimeout(Duration.ofSeconds(20))
                //.authenticator(Authenticator.getDefault())
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        //System.out.println(response.statusCode());
        //System.out.println(response.body());
        return Response.ok(response.body()).build();
    }

}
