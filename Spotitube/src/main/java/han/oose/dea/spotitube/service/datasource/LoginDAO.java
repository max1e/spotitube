package han.oose.dea.spotitube.service.datasource;

import han.oose.dea.spotitube.controllers.dto.LoginResponseDTO;

public interface LoginDAO {

    /**
     * Validates the users attempted login with database
     *
     * @param username The username of the user trying to log in
     * @param hashedPassword The hashed password of the user trying to log in
     * @return A LoginResponseDTO containing the users token and full name
     */
    LoginResponseDTO validateLogin(String username, String hashedPassword);
}