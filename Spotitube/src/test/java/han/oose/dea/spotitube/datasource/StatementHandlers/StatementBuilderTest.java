package han.oose.dea.spotitube.datasource.StatementHandlers;

import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import javax.ws.rs.InternalServerErrorException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("StatementBuilder unit tests")
public class StatementBuilderTest {

    private StatementBuilder sut;
    private Connection mockedConnection;

    private static final String PROCEDURE_NAME = "procedure";
    private static final String PARAMETER_1 = "param";
    private static final Integer PARAMETER_2 = 4;
    private static final Boolean PARAMETER_3 = true;
    private static final Object PARAMETER_4 = new Object();


    private static final PreparedStatement PREPARED_STATEMENT = Mockito.mock(PreparedStatement.class);

    @BeforeEach
    public void setup() {
        sut = new StatementBuilder();

        mockedConnection = Mockito.mock(Connection.class);
    }

    @Nested
    @DisplayName("build() unit tests")
    class BuildTest {
        @Test
        @DisplayName("Test build() returns a prepared statement with the right values set")
        public void testBuildReturnsAPreparedStatementWithTheRightValuesSet() throws SQLException {
            try {
                // Arrange
                Mockito.when(mockedConnection.prepareStatement("CALL " + PROCEDURE_NAME + "(?, ?, ?);")).thenReturn(PREPARED_STATEMENT);
                var expected = PREPARED_STATEMENT;

                // Act
                var actual = sut
                        .setConnection(mockedConnection)
                        .setProcedureName(PROCEDURE_NAME)
                        .addParameter(PARAMETER_1)
                        .addParameter(PARAMETER_2)
                        .addParameter(PARAMETER_3)
                        .build();

                // Assert
                assertEquals(expected, actual);
                Mockito.verify(PREPARED_STATEMENT).setString(1, PARAMETER_1);
                Mockito.verify(PREPARED_STATEMENT).setInt(2, PARAMETER_2);
                Mockito.verify(PREPARED_STATEMENT).setBoolean(3, PARAMETER_3);

            } catch (SQLException e) {
                fail();
            }

        }

        @Test
        @DisplayName("Test build() throws exception if invalid object is given")
        public void testBuildThrowsExceptionIfInvalidObjectIsGiven() {
            // Arrange
            try {
                Mockito.when(mockedConnection.prepareStatement("CALL " + PROCEDURE_NAME + "(?, ?, ?);")).thenReturn(PREPARED_STATEMENT);
                var expected = PREPARED_STATEMENT;

                // Act & Assert
                assertThrows(InternalServerErrorException.class, () -> sut
                        .setConnection(mockedConnection)
                        .setProcedureName(PROCEDURE_NAME)
                        .addParameter(PARAMETER_4)
                        .build());
            } catch (SQLException e) {
                fail();
            }
        }
    }
}
