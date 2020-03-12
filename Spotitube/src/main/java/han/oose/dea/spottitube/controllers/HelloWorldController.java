package han.oose.dea.spottitube.controllers;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/")
public class HelloWorldController {

    @GET
    public String helloWorld() {
        return "Hello world";
    }
}
