package han.oose.dea.spotitube.datasource.assembler.abstraction;

import han.oose.dea.spotitube.controllers.dto.LoginResponseDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface LoginResponseAssembler {

    LoginResponseDTO toLoginResponse(ResultSet resultset) throws SQLException;
}