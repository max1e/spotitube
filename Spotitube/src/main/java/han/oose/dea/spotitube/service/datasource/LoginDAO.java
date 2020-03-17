package han.oose.dea.spotitube.service.datasource;

import han.oose.dea.spotitube.controllers.dto.LoginResponseDTO;

public interface LoginDAO {
    boolean validateLogin(String username, String hashedPassword);
    boolean validateToken(String token);
    LoginResponseDTO getUserByToken(String username);
}