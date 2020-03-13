package han.oose.dea.spottitube.controllers.service;

import han.oose.dea.spottitube.controllers.dto.LoginResponseDTO;

public interface LoginService {

    public boolean doesLoginMatch(String user, String password);
    public LoginResponseDTO getLoginResponse(String user, String password);
    public boolean doesTokenMatch(String token);
}
