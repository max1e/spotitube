package han.oose.dea.spotitube.datasource.databaseConnection;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectorImpl implements DatabaseConnector{

    private DatabaseProperties databaseProperties;

    @Inject
    public void setDatabaseProperties(DatabaseProperties databaseProperties) {
        this.databaseProperties = databaseProperties;
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