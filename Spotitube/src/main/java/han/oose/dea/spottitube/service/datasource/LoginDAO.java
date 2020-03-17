package han.oose.dea.spottitube.service.datasource;

import han.oose.dea.spottitube.controllers.dto.LoginResponseDTO;

public interface LoginDAO {
    boolean validateLogin(String username, String hashedPassword);
    boolean validateToken(String token);
    LoginResponseDTO getUserToken(String username);
}