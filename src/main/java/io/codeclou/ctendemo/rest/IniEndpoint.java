package io.codeclou.ctendemo.rest;

import com.atlassian.plugins.rest.api.security.annotation.AdminOnly;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.apache.commons.configuration2.INIConfiguration;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
@Path("/ini/")
public class IniEndpoint {


    @GET
    @Path("/config")
    @AdminOnly
    public Response getIni() throws Exception {
        INIConfiguration iniConfiguration = new INIConfiguration();
        StringReader input = new StringReader("[foo]\nx=2\n[bar]\nx=4");
        Map<String, Map<String, String>> iniFileContents = new LinkedHashMap<>();
        iniConfiguration.read(input);
        if (iniConfiguration == null) {
            throw new RuntimeException("config is null");
        }
        List<String> result = new ArrayList<>();
        for (String section : iniConfiguration.getSections()) {
            result.add(section);
        }
        return Response.ok( result).build();
    }
}
