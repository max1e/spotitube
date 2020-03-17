package han.oose.dea.spottitube.service;

import han.oose.dea.spottitube.controllers.dto.LoginResponseDTO;
import han.oose.dea.spottitube.controllers.service.LoginService;
import han.oose.dea.spottitube.service.datasource.LoginDAO;
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

    // TODO unit testen
    @Override
    public LoginResponseDTO getLoginResponse(String username) {
        var loginResponse = loginDAO.getUserToken(username);
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