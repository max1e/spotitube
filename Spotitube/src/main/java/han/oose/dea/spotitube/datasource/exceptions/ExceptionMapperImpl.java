package han.oose.dea.spotitube.datasource.exceptions;

import javax.ws.rs.ForbiddenException;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotAuthorizedException;

public class ExceptionMapperImpl implements ExceptionMapper {

    public void mapException(Exception e) {
        RuntimeException exception;

        switch (e.getMessage()) {
            case "Unauthorized":
                exception = new NotAuthorizedException(e.getMessage());
                break;
            case "Forbidden":
                exception = new ForbiddenException(e.getMessage());
                break;
            default:
                e.printStackTrace();
                exception = new InternalServerErrorException("Something went horribly wrong!: ", e);
                break;
        }

        throw exception;
    }
}
