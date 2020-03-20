package han.oose.dea.spotitube.datasource.mappers.implementation;

import han.oose.dea.spotitube.controllers.dto.LoginResponseDTO;
import han.oose.dea.spotitube.datasource.mappers.abstraction.LoginResponseMapper;

import javax.enterprise.inject.Default;
import java.sql.ResultSet;
import java.sql.SQLException;

@Default
public class LoginResponseMapperImpl implements LoginResponseMapper {

    @Override
    public LoginResponseDTO toLoginResponse(ResultSet resultset) throws SQLException {
        resultset.next();

        var loginResponse = new LoginResponseDTO(
                resultset.getString("token"),
                resultset.getString("fullName")
        );

        return loginResponse;
    }
}