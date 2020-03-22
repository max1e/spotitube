package han.oose.dea.spotitube.service.datasource;

import han.oose.dea.spotitube.controllers.dto.LoginResponseDTO;

public interface LoginDAO {

    LoginResponseDTO validateLogin(String username, String hashedPassword);
}