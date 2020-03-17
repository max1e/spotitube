package han.oose.dea.spottitube.datasource;

import han.oose.dea.spottitube.controllers.dto.LoginResponseDTO;
import han.oose.dea.spottitube.datasource.util.DatabaseProperties;
import han.oose.dea.spottitube.service.datasource.LoginDAO;

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

//        loginAccepted = username.equals("plakplaatje")
//                && hashedPassword.equals(DigestUtils.sha256Hex(hashedPassword));

        return loginAccepted;
    }

    @Override
    public boolean validateToken(String token) {
        return token.equals("12345");
    }

    @Override
    public LoginResponseDTO getUserToken(String username) {
        return new LoginResponseDTO("12345", "plakplaatje");
    }
}
