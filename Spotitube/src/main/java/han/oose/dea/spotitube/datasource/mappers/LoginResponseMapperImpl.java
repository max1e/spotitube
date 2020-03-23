package han.oose.dea.spotitube.datasource.mappers;

import han.oose.dea.spotitube.controllers.dto.LoginResponseDTO;

import javax.enterprise.inject.Default;
import java.sql.ResultSet;
import java.sql.SQLException;

@Default
public class LoginResponseMapperImpl implements DTOMapper<LoginResponseDTO> {

    @Override
    public LoginResponseDTO toDTO(ResultSet resultset) throws SQLException {
        resultset.next();

        var loginResponse = new LoginResponseDTO(
                resultset.getString("token"),
                resultset.getString("fullName")
        );

        return loginResponse;
    }
}