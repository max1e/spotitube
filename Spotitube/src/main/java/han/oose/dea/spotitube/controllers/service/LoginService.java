package han.oose.dea.spotitube.controllers.service;

import han.oose.dea.spotitube.controllers.dto.LoginResponseDTO;

public interface LoginService {

    LoginResponseDTO validateLogin(String user, String password);
}
