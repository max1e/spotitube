package han.oose.dea.spotitube.datasource.databaseConnection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DatabaseConnectorTest {

    private DatabaseConnectorImpl sut;

    @BeforeEach
    public void setup() {
        sut = new DatabaseConnectorImpl();
    }

    @Nested
    @DisplayName("makeConnection() unit tests")
    class MakeConnectionTest {
        @Test
        @DisplayName("Test makeConnection() returns a connection")
        public void testMakeConnectionReturnsConnection() {
            try {
                // Arrange
                var expected = DriverManager.getConnection("jdbc:mysql://localhost/spotitube?serverTimezone=UTC&user=root&password=dbrules").getClass();

                // Act
                var actual =  sut.makeConnection().getClass();

                // Assert
                assertEquals(expected, actual);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @Nested
    @DisplayName("closeConnection() unit tests")
    class CloseConnectionTest {
        @Test
        @DisplayName("Test closeConnection() calls connection.close()")
        public void testCloseConnectionCallsConnectionClose() {
            try {
                // Arrange
                var mockedConnection = Mockito.mock(Connection.class);

                // Act
                sut.closeConnection(mockedConnection);

                // Assert
                Mockito.verify(mockedConnection).close();
            } catch (SQLException e) {
                fail();
            }
        }
    }
}
