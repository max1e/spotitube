package han.oose.dea.spotitube.datasource;

import han.oose.dea.spotitube.controllers.dto.LoginResponseDTO;
import han.oose.dea.spotitube.datasource.mappers.abstraction.LoginResponseMapper;
import han.oose.dea.spotitube.datasource.util.DatabaseProperties;
import han.oose.dea.spotitube.datasource.util.ExceptionMapper;
import han.oose.dea.spotitube.service.datasource.LoginDAO;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.ws.rs.InternalServerErrorException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Default
public class LoginDAOImpl implements LoginDAO {

    private DatabaseProperties databaseProperties;
    private Logger logger;

    private LoginResponseMapper loginResponseMapper;
    private ExceptionMapper exceptionMapper;

    public LoginDAOImpl() {
        databaseProperties = new DatabaseProperties();
        logger = Logger.getLogger(getClass().getName());
    }

    @Inject
    public void setLoginResponseMapper(LoginResponseMapper loginResponseMapper) {
        this.loginResponseMapper = loginResponseMapper;
    }

    @Inject
    public void setExceptionMapper(ExceptionMapper exceptionMapper) {
        this.exceptionMapper = exceptionMapper;
    }

    @Override
    public LoginResponseDTO validateLogin(String username, String hashedPassword) {
        LoginResponseDTO user = null;

        try {
            // Connect to database
            Class.forName(databaseProperties.getDriver());
            var connection = DriverManager.getConnection(databaseProperties.getConnectionString());

            // Query database
            var sqlQuery = "CALL sp_validateLogin(?, ?)";
            var sqlStatement = connection.prepareStatement(sqlQuery);

            sqlStatement.setString(1, username);
            sqlStatement.setString(2, hashedPassword);

            var resultset = sqlStatement.executeQuery();

            user = loginResponseMapper.toLoginResponse(resultset);
            resultset.next();

            // Close connection
            sqlStatement.close();
            connection.close();
        }
        catch (SQLException e) {
            exceptionMapper.mapException(e);
            logger.log(Level.SEVERE, "Error communicating with database: " + databaseProperties.getConnectionString(), e);
            throw new InternalServerErrorException("Something went horribly wrong!");
        }
        catch (ClassNotFoundException e) {
            logger.log(Level.SEVERE, "Error loading database driver: " + databaseProperties.getDriver(), e);
            throw new InternalServerErrorException("Something went horribly wrong!");
        }

        return user;
    }
}
