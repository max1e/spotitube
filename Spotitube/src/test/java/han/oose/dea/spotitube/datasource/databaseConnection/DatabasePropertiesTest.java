package han.oose.dea.spotitube.datasource.databaseConnection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DatabasePropertiesTest {

    private DatabaseProperties sut;

    @BeforeEach
    public void setup() {
        sut = new DatabaseProperties();
    }

    @Nested
    @DisplayName("getDriver() unit tests")
    class GetDriverTest {
        @Test
        @DisplayName("Test getDriver() fetches right driver")
        public void testGetDriverFetchesRightDriver() {
            // Arrange
            var expected = "com.mysql.cj.jdbc.Driver";

            // Act
            var actual = sut.getDriver();

            // Assert
            assertEquals(expected, actual);
        }
    }

    @Nested
    @DisplayName("getConnectionString() unit tests")
    class GetConnectionStringTest {
        @Test
        @DisplayName("Test getConnectionString() fetches right connection string")
        public void testGetConnectionStringFetchesRightConnectionString() {
            // Arrange
            var expected = "jdbc:mysql://localhost/spotitube?serverTimezone=UTC&user=root&password=dbrules";

            // Act
            var actual = sut.getConnectionString();

            // Assert
            assertEquals(expected, actual);
        }
    }
}
