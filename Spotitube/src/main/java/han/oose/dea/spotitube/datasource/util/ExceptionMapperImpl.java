package han.oose.dea.spotitube.datasource.util;

import javax.ws.rs.ForbiddenException;
import javax.ws.rs.NotAuthorizedException;
import java.sql.SQLException;

public class ExceptionMapperImpl implements ExceptionMapper {

    public void mapException(SQLException e) {
        if (e.getMessage().equals("Unauthorized")) {
            throw new NotAuthorizedException(e.getMessage());
        }
        if (e.getMessage().equals("Forbidden")) {
            throw new ForbiddenException(e.getMessage());
        }
    }
}
