package han.oose.dea.spotitube.controllers.service;

import han.oose.dea.spotitube.controllers.dto.LoginResponseDTO;

public interface LoginService {

    /**
     * Validates the users attempted login
     *
     * @param user The username of the user trying to log in
     * @param password The plain text password of the user trying to log in
     * @return A LoginResponseDTO containing the users token and full name
     */
    LoginResponseDTO validateLogin(String user, String password);
}
