package io.codeclou.ctendemo.rest;

import com.atlassian.plugins.rest.api.security.annotation.AdminOnly;

import io.codeclou.ctendemo.rest.model.OkModel;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
//import jakarta.json.stream.JsonGenerator;
//import jakarta.json.Json;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileWriter;
import java.io.OutputStream;
import java.io.StringWriter;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
@Path("/health/")
public class HealthEndpoint {

    @GET
    @Path("/test")
    @AdminOnly
    public Response getAtlassianConfluenceLog() throws Exception {
        /*StringWriter writer = new StringWriter();
        JsonGenerator gen = Json.createGenerator(writer);
        gen.writeStartObject()
                .write("firstName", "Duke")
                .write("lastName", "Java")
                .write("age", 18)
                .write("streetAddress", "100 Internet Dr")
                .write("city", "JavaTown")
                .write("state", "JA")
                .write("postalCode", "12345")
                .writeStartArray("phoneNumbers")
                .writeStartObject()
                .write("type", "mobile")
                .write("number", "111-111-1111")
                .writeEnd()
                .writeStartObject()
                .write("type", "home")
                .write("number", "222-222-2222")
                .writeEnd()
                .writeEnd()
                .writeEnd();
        gen.close();
        writer.toString()
        */
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
        System.out.println(response.statusCode());
        System.out.println(response.body());


        return Response.ok( "ok").build();
    }

}