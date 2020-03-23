package han.oose.dea.spotitube.datasource;

import han.oose.dea.spotitube.controllers.dto.LoginResponseDTO;
import han.oose.dea.spotitube.datasource.StatementHandlers.StatementBuilder;
import han.oose.dea.spotitube.datasource.databaseConnection.DatabaseConnector;
import han.oose.dea.spotitube.datasource.exceptions.ExceptionMapper;
import han.oose.dea.spotitube.datasource.mappers.DTOMapper;
import han.oose.dea.spotitube.service.datasource.LoginDAO;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
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

            var sqlStatement = new StatementBuilder()
                    .setConnection(connection)
                    .setProcedureName("sp_validateLogin")
                    .addParameter(username)
                    .addParameter(hashedPassword)
                    .build();

            var resultset = sqlStatement.executeQuery();

            user = loginResponseMapper.toDTO(resultset);
            resultset.next();

            closeConnection(connection, sqlStatement);
        }
        catch (SQLException | ClassNotFoundException e) {
            exceptionMapper.mapException(e);
        }

        return user;
    }

    private void closeConnection(Connection connection, PreparedStatement sqlStatement) throws SQLException {
        sqlStatement.close();
        dbConnector.closeConnection(connection);
    }
}
