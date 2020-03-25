package han.oose.dea.spotitube.datasource.databaseConnection;

import java.sql.Connection;
import java.sql.SQLException;

public interface DatabaseConnector {

    /**
     * Loads database driver and creates a connection to the database
     *
     * @return Connection to database
     * @throws ClassNotFoundException The driver could not be loaded
     * @throws SQLException Something went wrong with creating a connection to the database
     */
    Connection makeConnection() throws ClassNotFoundException, SQLException;

    /**
     * Closes connection with database
     *
     * @param connection The database connection to be closed
     * @throws SQLException Something went wrong with closing the connection
     */
    void closeConnection(Connection connection) throws SQLException;
}
