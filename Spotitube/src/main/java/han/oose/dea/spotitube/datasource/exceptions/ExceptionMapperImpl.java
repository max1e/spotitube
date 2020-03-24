package han.oose.dea.spotitube.datasource.exceptions;

import han.oose.dea.spotitube.controllers.exceptions.InvalidCredentialsException;

import javax.ws.rs.ForbiddenException;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotAuthorizedException;

public class ExceptionMapperImpl implements ExceptionMapper {

    public void mapException(Exception e) {
        RuntimeException exception;

        switch (e.getMessage()) {
            case "Invalid credentials":
                exception = new InvalidCredentialsException();
                break;
            case "Unauthorized":
                exception = new NotAuthorizedException("You are not authenticated, please log in.");
                break;
            case "Forbidden":
                exception = new ForbiddenException("You are not authorized to do this.");
                break;
            default:
                exception = new InternalServerErrorException("Oops, something went wrong, please try again.");
                break;
        }

        throw exception;
    }
}
