package han.oose.dea.spotitube.controllers.exceptions.mappers;

import han.oose.dea.spotitube.controllers.exceptions.InvalidCredentialsException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class InvalidCredentialsMapper implements ExceptionMapper<InvalidCredentialsException> {

    @Override
    public Response toResponse(InvalidCredentialsException e) {
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }
}
