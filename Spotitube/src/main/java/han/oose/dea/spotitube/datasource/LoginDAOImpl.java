package han.oose.dea.spotitube.datasource;

import han.oose.dea.spotitube.controllers.dto.LoginResponseDTO;
import han.oose.dea.spotitube.datasource.databaseConnection.DatabaseConnector;
import han.oose.dea.spotitube.datasource.exceptions.ExceptionMapper;
import han.oose.dea.spotitube.datasource.mappers.DTOMapper;
import han.oose.dea.spotitube.service.datasource.LoginDAO;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.sql.SQLException;

@Default
public class LoginDAOImpl implements LoginDAO {

    private DatabaseConnector dbConnector;

    private DTOMapper<LoginResponseDTO> loginResponseMapper;
    private ExceptionMapper exceptionMapper;

    @Inject
    public void setLoginResponseMapper(DTOMapper<LoginResponseDTO> loginResponseMapper) {
        this.loginResponseMapper = loginResponseMapper;
    }

    @Inject
    public void setExceptionMapper(ExceptionMapper exceptionMapper) {
        this.exceptionMapper = exceptionMapper;
    }

    @Inject
    public void setDbConnector(DatabaseConnector dbConnector) {
        this.dbConnector = dbConnector;
    }

    @Override
    public LoginResponseDTO validateLogin(String username, String hashedPassword) {
        LoginResponseDTO user = null;

        try {
            var connection = dbConnector.makeConnection();

            // Query database
            var sqlQuery = "CALL sp_validateLogin(?, ?)";
            var sqlStatement = connection.prepareStatement(sqlQuery);

            sqlStatement.setString(1, username);
            sqlStatement.setString(2, hashedPassword);

            var resultset = sqlStatement.executeQuery();

            user = loginResponseMapper.toDTO(resultset);
            resultset.next();

            // Close connection
            sqlStatement.close();
            connection.close();
        }
        catch (SQLException | ClassNotFoundException e) {
            exceptionMapper.mapException(e);
        }

        return user;
    }
}
