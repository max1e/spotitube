package han.oose.dea.spotitube.controllers;

import han.oose.dea.spotitube.controllers.dto.TracksDTO;
import han.oose.dea.spotitube.controllers.service.LoginService;
import han.oose.dea.spotitube.controllers.service.TracksService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("TracksController unit tests")
public class TracksControllerTest {

    private TracksController sut;
    private LoginService mockedLoginService;
    private TracksService mockedTracksService;

    private static final String TOKEN = "1234-1234-1234";
    private static final int PLAYLIST_ID = 1;

    private static final TracksDTO TRACKS_DTO = new TracksDTO();

    @BeforeEach
    public void setup() {
        sut = new TracksController();

        mockedLoginService = Mockito.mock(LoginService.class);
        sut.setLoginService(mockedLoginService);

        mockedTracksService = Mockito.mock(TracksService.class);
        sut.setTracksService(mockedTracksService);
    }

    @Nested
    @DisplayName("getAvailableTracks() unit tests")
    class GetAvailableTracksTest {
        @Test
        @DisplayName("Test getAvailableTracks() passes on tracks if token matches")
        public void testGetAvailableTracksPassesOnTracksIfTokenMatches() {
            // Arrange
            Mockito.when(mockedLoginService.validateToken(TOKEN)).thenReturn(true);
            Mockito.when(mockedTracksService.getAvailableTracks(PLAYLIST_ID)).thenReturn(TRACKS_DTO);

            var expectedStatus = Response.Status.OK.getStatusCode();
            var expectedEntity = TRACKS_DTO;

            // Act
            var response = sut.getAvailableTracks(TOKEN, PLAYLIST_ID);

            var actualStatus = response.getStatus();
            var actualEntity = response.getEntity();

            // Assert
            assertEquals(expectedStatus, actualStatus);
            assertEquals(expectedEntity, actualEntity);
        }
    }
}
