package han.oose.dea.spotitube.datasource;

import han.oose.dea.spotitube.controllers.dto.LoginResponseDTO;
import han.oose.dea.spotitube.datasource.util.DatabaseProperties;
import han.oose.dea.spotitube.service.datasource.LoginDAO;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginDAOImpl implements LoginDAO {

    private DatabaseProperties databaseProperties;
    private Logger logger;

    public LoginDAOImpl() {
        databaseProperties = new DatabaseProperties();
        logger = Logger.getLogger(getClass().getName());
    }

    @Override
    public boolean validateLogin(String username, String hashedPassword) {
        var loginAccepted = false;

        try {
            // Connect to database
            var connection = DriverManager.getConnection(databaseProperties.getConnectionString());

            // Query database
            var sqlQuery = "CALL sp_validateLogin(?, ?)";
            var sqlStatement = connection.prepareStatement(sqlQuery);

            sqlStatement.setString(1, username);
            sqlStatement.setString(2, hashedPassword);

            var resultSet = sqlStatement.executeQuery();

            // Read result set
            resultSet.next();
            loginAccepted = resultSet.getBoolean("loginAccepted");

            // Close connection
            sqlStatement.close();
            connection.close();
        }
        catch (SQLException e) {
            logger.log(Level.SEVERE, "Error communicating with database: " + databaseProperties.getConnectionString(), e);
        }

        return loginAccepted;
    }

    @Override
    public boolean validateToken(String token) {
        var tokenAccepted = false;

        try {
            // Connect to database
            var connection = DriverManager.getConnection(databaseProperties.getConnectionString());

            // Query database
            var sqlQuery = "CALL sp_validateToken(?)";
            var sqlStatement = connection.prepareStatement(sqlQuery);

            sqlStatement.setString(1, token);

            var resultSet = sqlStatement.executeQuery();

            // Read result set
            resultSet.next();
            tokenAccepted = resultSet.getBoolean("tokenAccepted");

            // Close connection
            sqlStatement.close();
            connection.close();
        }
        catch (SQLException e) {
            logger.log(Level.SEVERE, "Error communicating with database: " + databaseProperties.getConnectionString(), e);
        }

        return tokenAccepted;
    }

    @Override
    public LoginResponseDTO getUserByToken(String username) {
        return new LoginResponseDTO("12345", "plakplaatje");
    }
}
