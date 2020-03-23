package han.oose.dea.spotitube.datasource.mappers;

import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@DisplayName("LoginResponse unit tests")
public class LoginResponseMapperTest {

    private LoginResponseMapperImpl sut;
    private ResultSet mockedResultset;

    private static final String TOKEN = "1234-1234-1234";
    private static final String FULL_NAME = "Full name";

    @BeforeEach
    public void setup() {
        sut = new LoginResponseMapperImpl();

        mockedResultset = Mockito.mock(ResultSet.class);
    }

    @Nested
    @DisplayName("toDTO() unit tests")
    class ToDTOTest {
        @Test
        @DisplayName("Test toDTO() maps resultset")
        public void testToLoginResponseMapsResultset() {
            try {
                // Arrange
                Mockito.when(mockedResultset.getString("token")).thenReturn(TOKEN);
                Mockito.when(mockedResultset.getString("fullName")).thenReturn(FULL_NAME);

                var expectedToken = TOKEN;
                var expectedFullName = FULL_NAME;

                // Act
                var loginResponse = sut.toDTO(mockedResultset);

                var actualToken = loginResponse.getToken();
                var actualFullName = loginResponse.getUser();

                // Assert
                assertEquals(expectedToken, actualToken);
                assertEquals(expectedFullName, actualFullName);
            } catch (SQLException e) {
                fail();
            }
        }
    }
}
