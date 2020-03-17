package han.oose.dea.spottitube.controllers.service;

import han.oose.dea.spottitube.controllers.dto.LoginResponseDTO;

public interface LoginService {

    boolean validateLogin(String user, String password);
    LoginResponseDTO getLoginResponse(String user);
    boolean validateToken(String token);
}
