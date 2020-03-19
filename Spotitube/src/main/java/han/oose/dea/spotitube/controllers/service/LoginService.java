package han.oose.dea.spotitube.controllers.service;

import han.oose.dea.spotitube.controllers.dto.LoginResponseDTO;

public interface LoginService {

    boolean validateLogin(String user, String password);
    LoginResponseDTO getLoginResponse(String user, String password);
    boolean validateToken(String token);
}
