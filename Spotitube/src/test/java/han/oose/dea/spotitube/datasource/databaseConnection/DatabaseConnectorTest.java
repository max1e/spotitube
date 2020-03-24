package han.oose.dea.spotitube.datasource.databaseConnection;

import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DatabaseConnectorTest {

    private DatabaseConnectorImpl sut;
    private DatabaseProperties mockedDatabaseProperties;

    private static final String CONNECTION_STRING = "jdbc:mysql://localhost/spotitube?serverTimezone=UTC&user=root&password=dbrules";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static Connection CONNECTION;

    @BeforeAll
    public static void variableInit() {
        try {
            CONNECTION = DriverManager.getConnection(CONNECTION_STRING);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @BeforeEach
    public void setup() {
        sut = new DatabaseConnectorImpl();

        mockedDatabaseProperties = Mockito.mock(DatabaseProperties.class);
        sut.setDatabaseProperties(mockedDatabaseProperties);
    }

    @Nested
    @DisplayName("makeConnection() unit tests")
    class MakeConnectionTest {

        @Test
        @DisplayName("Test makeConnection() calls a connectionString.getDriver")
        public void testMakeConnectionCallsGetDriver() {
            try {
                // Arrange
                Mockito.when(mockedDatabaseProperties.getDriver()).thenReturn(DRIVER);
                Mockito.when(mockedDatabaseProperties.getConnectionString()).thenReturn(CONNECTION_STRING);

                // Act
                sut.makeConnection();

                // Assert
                Mockito.verify(mockedDatabaseProperties).getDriver();
            } catch (SQLException | ClassNotFoundException e) {
                fail();
            }
        }

        @Test
        @DisplayName("Test makeConnection() calls a connectionString.getConnectionString")
        public void testMakeConnectionCallsGetConnectionString() {
            try {
                // Arrange
                Mockito.when(mockedDatabaseProperties.getDriver()).thenReturn(DRIVER);
                Mockito.when(mockedDatabaseProperties.getConnectionString()).thenReturn(CONNECTION_STRING);

                // Act
                sut.makeConnection();

                // Assert
                Mockito.verify(mockedDatabaseProperties).getConnectionString();
            } catch (SQLException | ClassNotFoundException e) {
                fail();
            }
        }

        @Test
        @DisplayName("Test makeConnection() returns a connection")
        public void testMakeConnectionReturnsConnection() {
            try {
                // Arrange
                Mockito.when(mockedDatabaseProperties.getDriver()).thenReturn(DRIVER);
                Mockito.when(mockedDatabaseProperties.getConnectionString()).thenReturn(CONNECTION_STRING);
                var expected = CONNECTION.getClass();

                // Act
                var actual =  sut.makeConnection().getClass();

                // Assert
                assertEquals(expected, actual);
            } catch (SQLException | ClassNotFoundException e) {
                fail();
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
