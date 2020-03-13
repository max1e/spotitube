package han.oose.dea.spottitube.service;

import han.oose.dea.spottitube.controllers.dto.LoginResponseDTO;
import han.oose.dea.spottitube.controllers.service.LoginService;

import javax.ws.rs.ForbiddenException;

public class LoginServiceImpl implements LoginService {

    @Override
    public boolean doesLoginMatch(String user, String password) {
        var loginMatches = user.equals("plakplaatje") && password.equals("wachtwoord");
        return loginMatches;
    }

    @Override
    public LoginResponseDTO getLoginResponse(String user, String password) {
        LoginResponseDTO loginResponse;

        // Second check in case code is wrongly implemented
        if (!doesLoginMatch(user, password)) {
            throw new ForbiddenException();
        }
        else {
            loginResponse = new LoginResponseDTO("12345", "plakplaatje");
        }

        return loginResponse;
    }

    @Override
    public boolean doesTokenMatch(String token) {
        return token.equals("12345");
    }
}
