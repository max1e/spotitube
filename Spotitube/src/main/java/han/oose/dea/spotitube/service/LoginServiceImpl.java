package han.oose.dea.spotitube.service;

import han.oose.dea.spotitube.controllers.dto.LoginResponseDTO;
import han.oose.dea.spotitube.controllers.service.LoginService;
import han.oose.dea.spotitube.service.datasource.LoginDAO;
import org.apache.commons.codec.digest.DigestUtils;

import javax.inject.Inject;

public class LoginServiceImpl implements LoginService {

    private LoginDAO loginDAO;

    @Inject
    public void setLoginDAO(LoginDAO loginDAO) {
        this.loginDAO = loginDAO;
    }

    @Override
    public boolean validateLogin(String username, String password) {
        var hashedPassword = hashPassword(password);
        var loginMatches = loginDAO.validateLogin(username, hashedPassword);
        return loginMatches;
    }

    @Override
    public LoginResponseDTO getLoginResponse(String username, String password) {
        var hashedPassword = hashPassword(password);
        var loginResponse = loginDAO.getUserAndToken(username, hashedPassword);
        return loginResponse;
    }

    @Override
    public boolean validateToken(String token) {
        var tokenMatches = loginDAO.validateToken(token);
        return tokenMatches;
    }

    private String hashPassword(String password) {
        var hashedPassword = DigestUtils.sha256Hex(password);
        return hashedPassword;
    }
}