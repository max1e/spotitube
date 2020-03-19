package han.oose.dea.spotitube.controllers;

import han.oose.dea.spotitube.controllers.dto.LoginDTO;
import han.oose.dea.spotitube.controllers.dto.LoginResponseDTO;
import han.oose.dea.spotitube.controllers.service.LoginService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@DisplayName("LoginController unit tests")
public class LoginControllerTest {

    private LoginController sut;
    private LoginService mockedLoginService;

    private static final String TOKEN = "1234-1234-1234";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";

    private LoginDTO loginDTO = new LoginDTO(USERNAME, PASSWORD);;

    @BeforeEach
    public void setup() {
        sut = new LoginController();

        mockedLoginService = Mockito.mock(LoginService.class);
        sut.setLoginService(mockedLoginService);
    }

    @Nested
    @DisplayName("handleLogin() unit tests")
    class HandleLoginTest {
        @Test
        @DisplayName("Test handleLogin() returns unauthorized if login does not match")
        public void testHandleLoginReturnsUnauthorizedIfLoginDoesntMatch() {
            // Arrange
            Mockito.when(mockedLoginService.validateLogin(USERNAME, PASSWORD)).thenReturn(false);

            var expectedStatus = Response.Status.UNAUTHORIZED.getStatusCode();

            // Act
            var response = sut.handleLogin(loginDTO);

            var actualStatus = response.getStatus();
            var actualEntity = response.getEntity();

            // Assert
            assertEquals(expectedStatus, actualStatus);
            assertNull(actualEntity);
        }

        @Test
        @DisplayName("Test handleLogin() returns login response if login matched")
        public void testHandleLoginReturnsLoginResponseIfLoginMatched() {
            // Arrange
            var loginResponseDTO = new LoginResponseDTO(TOKEN, USERNAME);

            Mockito.when(mockedLoginService.validateLogin(USERNAME, PASSWORD)).thenReturn(true);
            Mockito.when(mockedLoginService.getLoginResponse(USERNAME, PASSWORD)).thenReturn(loginResponseDTO);

            var expectedStatus = Response.Status.OK.getStatusCode();
            var expectedEntity = loginResponseDTO;

            // Act
            var response = sut.handleLogin(loginDTO);

            var actualStatus = response.getStatus();
            var actualEntity = response.getEntity();

            // Assert
            assertEquals(expectedStatus, actualStatus);
            assertEquals(expectedEntity, actualEntity);
        }
    }
}
