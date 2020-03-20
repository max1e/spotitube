package han.oose.dea.spotitube.datasource.util;

import javax.ws.rs.ForbiddenException;
import javax.ws.rs.NotAuthorizedException;
import java.sql.SQLException;

public class ExceptionMapper {

    // TODO niet static maken
    public static void mapException(SQLException e) {
        System.out.println(e.getMessage());
        if (e.getMessage().equals("Unauthorized")) {
            throw new NotAuthorizedException(e.getMessage());
        }
        if (e.getMessage().equals("Forbidden")) {
            throw new ForbiddenException(e.getMessage());
        }

    }
}
