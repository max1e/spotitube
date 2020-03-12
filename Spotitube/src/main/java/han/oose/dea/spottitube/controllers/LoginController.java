package han.oose.dea.spottitube.controllers;

import han.oose.dea.spottitube.controllers.dto.LoginDTO;
import han.oose.dea.spottitube.controllers.dto.LoginResponseDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/login")
public class LoginController {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response handleLogin(LoginDTO loginDTO) {
        Response response;

        if (!doesLoginMatch(loginDTO.getUser(), loginDTO.getPassword())) {
            response = Response.status(Response.Status.UNAUTHORIZED).build();
        }
        else {
            var loginResponse = new LoginResponseDTO("12345", "plakplaatje");
            response = Response.status(Response.Status.ACCEPTED).entity(loginResponse).build();
        }

        return response;
    }

    private boolean doesLoginMatch(String user, String password) {
        var loginMatches = user.equals("plakplaatje") && password.equals("wachtwoord");
        return loginMatches;
    }
}