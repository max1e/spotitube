package han.oose.dea.spottitube.controllers;

import han.oose.dea.spottitube.controllers.dto.LoginDTO;
import han.oose.dea.spottitube.controllers.service.LoginService;

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

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response handleLogin(LoginDTO login) {
        Response response;

        if (!loginService.validateLogin(login.getUser(), login.getPassword())) {
            response = Response.status(Response.Status.UNAUTHORIZED).build();
        }
        else {
            var loginResponse = loginService.getLoginResponse(login.getUser());
            response = Response.status(Response.Status.OK).entity(loginResponse).build();
        }

        return response;
    }
}