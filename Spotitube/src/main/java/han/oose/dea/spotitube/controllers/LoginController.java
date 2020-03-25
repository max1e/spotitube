package han.oose.dea.spotitube.controllers;

import han.oose.dea.spotitube.controllers.dto.LoginDTO;
import han.oose.dea.spotitube.controllers.service.LoginService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/login")
public class LoginController {

    private LoginService loginService;

    @Inject
    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }

    /**
     * Validates the users attempted login
     *
     * @param login A loginDTO containing the username and password of the user trying to log in
     * @return Response OK containing the users token and full name
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response handleLogin(LoginDTO login) {
        var loginResponse = loginService.validateLogin(login.getUser(), login.getPassword());
        var response = Response.status(Response.Status.OK).entity(loginResponse).build();

        return response;
    }
}