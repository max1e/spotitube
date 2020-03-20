package han.oose.dea.spotitube.datasource.mappers.abstraction;

import han.oose.dea.spotitube.controllers.dto.LoginResponseDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface LoginResponseMapper {

    LoginResponseDTO toLoginResponse(ResultSet resultset) throws SQLException;
}