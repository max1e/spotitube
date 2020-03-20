package han.oose.dea.spotitube.datasource.util;

import java.sql.SQLException;

public interface ExceptionMapper {

    void mapException(SQLException e);
}
