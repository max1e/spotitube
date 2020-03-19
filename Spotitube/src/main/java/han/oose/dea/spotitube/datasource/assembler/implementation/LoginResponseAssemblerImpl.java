package han.oose.dea.spotitube.datasource.assembler.implementation;

import han.oose.dea.spotitube.controllers.dto.LoginResponseDTO;
import han.oose.dea.spotitube.datasource.assembler.abstraction.LoginResponseAssembler;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginResponseAssemblerImpl implements LoginResponseAssembler {

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