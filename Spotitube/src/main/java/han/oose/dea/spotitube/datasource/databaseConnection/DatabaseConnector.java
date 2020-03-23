package han.oose.dea.spotitube.datasource.databaseConnection;

import java.sql.Connection;
import java.sql.SQLException;

public interface DatabaseConnector {

    Connection makeConnection() throws ClassNotFoundException, SQLException;
    void closeConnection(Connection connection) throws SQLException;
}
