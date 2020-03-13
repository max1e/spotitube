package nl.han.ica.oose.spottitube.controllers;

import han.oose.dea.spottitube.controllers.LoginController;
import han.oose.dea.spottitube.controllers.dto.LoginDTO;
import han.oose.dea.spottitube.controllers.dto.LoginResponseDTO;
import han.oose.dea.spottitube.controllers.service.LoginService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@DisplayName("LoginController unit tests")
public class LoginControllerTest {

    private LoginController sut;
    private LoginService mockedLoginService;
    private LoginDTO loginDTO;

    private static final String TOKEN = "1234-1234-1234";
    private static final String USER = "username";
    private static final String PASSWORD = "password";

    private static final int HTTP_OK = 200;
    private static final int HTTP_UNAUTHORIZED = 401;

    @BeforeEach
    public void setup() {
        sut = new LoginController();

        mockedLoginService = Mockito.mock(LoginService.class);
        sut.setLoginService(mockedLoginService);

        loginDTO = new LoginDTO(USER, PASSWORD);
    }

    @Test
    @DisplayName("Test handleLogin returns unauthorized if login does not match")
    public void testHandleLoginReturnsUnauthorizedIfLoginDoesntMatch() {
        // Arrange
        Mockito.when(mockedLoginService.doesLoginMatch(USER, PASSWORD)).thenReturn(false);

        var expectedStatus = HTTP_UNAUTHORIZED;

        // Act
        var response = sut.handleLogin(loginDTO);

        // Assert
        assertEquals(expectedStatus, response.getStatus());
        assertNull(response.getEntity());
    }

    @Test
    @DisplayName("Test handleLogin returns login response if login matched")
    public void testHandleLoginReturnsLoginResponseIfLoginMatched() {
        // Arrange
        var loginResponseDTO = new LoginResponseDTO(TOKEN, USER);

        Mockito.when(mockedLoginService.doesLoginMatch(USER, PASSWORD)).thenReturn(true);
        Mockito.when(mockedLoginService.getLoginResponse(USER, PASSWORD)).thenReturn(loginResponseDTO);

        var expectedStatus = HTTP_OK;
        var expectedEntity = loginResponseDTO;

        // Act
        var response = sut.handleLogin(loginDTO);

        // Assert
        assertEquals(expectedStatus, response.getStatus());
        assertEquals(expectedEntity, response.getEntity());
    }

}
