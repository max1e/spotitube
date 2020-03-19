package han.oose.dea.spotitube.datasource;

import han.oose.dea.spotitube.controllers.dto.LoginResponseDTO;
import han.oose.dea.spotitube.datasource.assembler.abstraction.LoginResponseAssembler;
import han.oose.dea.spotitube.datasource.util.DatabaseProperties;
import han.oose.dea.spotitube.service.datasource.LoginDAO;

import javax.inject.Inject;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginDAOImpl implements LoginDAO {

    private DatabaseProperties databaseProperties;
    private Logger logger;
    private LoginResponseAssembler loginResponseAssembler;

    public LoginDAOImpl() {
        databaseProperties = new DatabaseProperties();
        logger = Logger.getLogger(getClass().getName());
    }

    @Inject
    public void setLoginResponseAssembler(LoginResponseAssembler loginResponseAssembler) {
        this.loginResponseAssembler = loginResponseAssembler;
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

            var resultset = sqlStatement.executeQuery();

            // Read result set
            resultset.next();
            loginAccepted = resultset.getBoolean("loginAccepted");

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

            var resultset = sqlStatement.executeQuery();

            // Read result set
            resultset.next();
            tokenAccepted = resultset.getBoolean("tokenAccepted");

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
    public LoginResponseDTO getUserAndToken(String username, String hashedPassword) {
        LoginResponseDTO userAndToken = null;

        try {
            // Connect to database
            var connection = DriverManager.getConnection(databaseProperties.getConnectionString());

            // Query database
            var sqlQuery = "CALL sp_getUserAndToken(?, ?)";
            var sqlStatement = connection.prepareStatement(sqlQuery);

            sqlStatement.setString(1, username);
            sqlStatement.setString(2, hashedPassword);

            var resultset = sqlStatement.executeQuery();

            // Read result set
            userAndToken = loginResponseAssembler.toLoginResponse(resultset);

            // Close connection
            sqlStatement.close();
            connection.close();
        }
        catch (SQLException e) {
            logger.log(Level.SEVERE, "Error communicating with database: " + databaseProperties.getConnectionString(), e);
        }

        // TODO moet eigenlijk een soort error gaan geven als het null is denk ik zo, maar dat lijkt me meer wat voor in de servicelaag
        return userAndToken;
    }
}
