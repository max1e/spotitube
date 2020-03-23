package han.oose.dea.spotitube.datasource.databaseConnection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

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
