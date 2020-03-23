package han.oose.dea.spotitube.datasource.databaseConnection;

import han.oose.dea.spotitube.datasource.exceptions.ExceptionMapper;
import han.oose.dea.spotitube.datasource.exceptions.ExceptionMapperImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import javax.ws.rs.NotAuthorizedException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class DatabaseConnectorTest {

    private DatabaseConnectorImpl sut;

    @BeforeEach
    public void setup() {
        sut = new DatabaseConnectorImpl();
    }

    @Nested
    @DisplayName("makeConnection() unit tests")
    class MapExceptionTest {
        @Test
        @DisplayName("Test makeConnection() maps to unauthorized exception")
        public void testMapExceptionMapsToUnauthorized() {
            // TODO test verzinnen
            // Arrange

            // Act

            // Assert
        }
    }
}
