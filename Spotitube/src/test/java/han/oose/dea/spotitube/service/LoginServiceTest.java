package han.oose.dea.spotitube.service;

import han.oose.dea.spotitube.controllers.dto.LoginResponseDTO;
import han.oose.dea.spotitube.service.datasource.LoginDAO;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("LoginService unit tests")
public class LoginServiceTest {

    private LoginServiceImpl sut;
    private LoginDAO mockedLoginDAO;

    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String HASHED_PASSWORD = DigestUtils.sha256Hex(PASSWORD);
    private static final String TOKEN = "1234-1234-1234";

    private static final LoginResponseDTO LOGIN_RESPONSE = new LoginResponseDTO(TOKEN, HASHED_PASSWORD);

    @BeforeEach
    public void setup() {
        sut = new LoginServiceImpl();

        mockedLoginDAO = Mockito.mock(LoginDAO.class);
        sut.setLoginDAO(mockedLoginDAO);
    }

    @Nested
    @DisplayName("validateLogin() unit tests")
    class ValidateLoginTest {
        @Test
        @DisplayName("Test validateLogin() calls loginDAO.validateLogin()")
        public void testValidateLoginCallsLoginDAOValidateLogin() {
            // Arrange
            var expectedPassword = HASHED_PASSWORD;

            // Act
            sut.validateLogin(USERNAME, PASSWORD);

            // Assert
            Mockito.verify(mockedLoginDAO).validateLogin(USERNAME, expectedPassword);
        }

        @Test
        @DisplayName("Test validateLogin() passes on boolean from loginDAO.validateLogin()")
        public void testValidateLoginPassesOnBooleanFromLoginDAOValidateLogin() {
            // Assert
            var expected = LOGIN_RESPONSE;

            Mockito.when(mockedLoginDAO.validateLogin(USERNAME, HASHED_PASSWORD)).thenReturn(LOGIN_RESPONSE);

            // Act
            var actual = sut.validateLogin(USERNAME, PASSWORD);

            // Assert
            assertEquals(expected, actual);
        }
    }

    @Nested
    @DisplayName("validateToken() unit tests")
    class ValidateTokenTest {
        @Test
        @DisplayName("Test validateToken() calls loginDAO.validateToken()")
        public void testvalidateTokenCallsLoginDAOValidateLogin() {
            // Arrange

            // Act
            sut.validateToken(TOKEN);

            // Assert
            Mockito.verify(mockedLoginDAO).validateToken(TOKEN);
        }

        @Test
        @DisplayName("Test validateToken() passes on boolean from loginDAO.validateToken()")
        public void testValidateTokenPassesOnBooleanFromLoginDAOValidateToken() {
            // Assert
            var expected = true;

            Mockito.when(mockedLoginDAO.validateToken(TOKEN)).thenReturn(expected);

            // Act
            var actual = sut.validateToken(TOKEN);

            // Assert
            assertEquals(expected, actual);
        }
    }
}