package han.oose.dea.spotitube.datasource.exceptions;

import han.oose.dea.spotitube.controllers.exceptions.InvalidCredentialsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import javax.ws.rs.ForbiddenException;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotAuthorizedException;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("ExceptionMapper unit tests")
public class ExceptionMapperTest {

    private ExceptionMapper sut;

    @BeforeEach
    public void setup() {
        sut = new ExceptionMapperImpl();
    }

    @Nested
    @DisplayName("mapException() unit tests")
    class MapExceptionTest {
        @Test
        @DisplayName("Test mapException() maps to invalid credentials exception")
        public void testMapExceptionMapsToInvalidcredentials() {
            // Arrange
            var testValue = "Precondition failed";
            var sqlException = new SQLException(testValue);

            // Act & Assert
            assertThrows(InvalidCredentialsException.class, () -> sut.mapException(sqlException));
        }

        @Test
        @DisplayName("Test mapException() maps to unauthorized exception")
        public void testMapExceptionMapsToUnauthorized() {
            // Arrange
            var testValue = "Unauthorized";
            var sqlException = new SQLException(testValue);

            // Act & Assert
            assertThrows(NotAuthorizedException.class, () -> sut.mapException(sqlException));
        }

        @Test
        @DisplayName("Test mapException() maps to forbidden exception")
        public void testMapExceptionMapsToForbidden() {
            // Arrange
            var testValue = "Forbidden";
            var sqlException = new SQLException(testValue);

            // Act & Assert
            assertThrows(ForbiddenException.class, () -> sut.mapException(sqlException));
        }

        @Test
        @DisplayName("Test mapException() maps to internal server error exception")
        public void testMapExceptionMapsToInternalServerError() {
            // Arrange
            var testValue = "Class not found";
            var exception = new ClassNotFoundException(testValue);

            // Act & Assert
            assertThrows(InternalServerErrorException.class, () -> sut.mapException(exception));
        }
    }
}