package han.oose.dea.spotitube.datasource.databaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectorImpl implements DatabaseConnector{

    private DatabaseProperties databaseProperties;

    public DatabaseConnectorImpl() {
        databaseProperties = new DatabaseProperties();
    }

    @Override
    public Connection makeConnection() throws ClassNotFoundException, SQLException {
        Class.forName(databaseProperties.getDriver());
        var connection = DriverManager.getConnection(databaseProperties.getConnectionString());
        return connection;
    }

    @Override
    public void closeConnection(Connection connection) throws SQLException {
        connection.close();
    }
}