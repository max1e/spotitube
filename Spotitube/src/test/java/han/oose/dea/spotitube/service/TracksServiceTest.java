package han.oose.dea.spotitube.service;

import han.oose.dea.spotitube.controllers.dto.TrackDTO;
import han.oose.dea.spotitube.service.datasource.TrackDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("TracksService unit tests")
public class TracksServiceTest {

    private TracksServiceImpl sut;
    private TrackDAO mockedTrackDAO;

    private static final String TOKEN = "1234-1234-1234";
    private static final int PLAYLIST_ID = 1;
    private static final Integer EMPTY_PLAYLIST_ID = null;

    private static final List<TrackDTO> TRACKS = new ArrayList<>();

    @BeforeEach
    public void setup() {
        sut = new TracksServiceImpl();

        mockedTrackDAO = Mockito.mock(TrackDAO.class);
        sut.setTrackDAO(mockedTrackDAO);
    }

    @Nested
    @DisplayName("getAvailableTracks() unit tests")
    class GetAvailableTracksTest {
        @Test
        @DisplayName("Test getAvailableTracks() calls trackDAO.getTracksNotInPlaylist() if playlistId is set")
        public void testGetAvailableTracksCallsTrackDAOGetTracksNotInPlaylistIfPlaylistIdIsSet() {
            // Arrange

            // Act
            sut.getAvailableTracks(TOKEN, PLAYLIST_ID);

            // Assert
            Mockito.verify(mockedTrackDAO).getTracksNotInPlaylist(TOKEN, PLAYLIST_ID);
        }

        @Test
        @DisplayName("Test getAvailableTracks() passes on tracks from trackDAO.getTracksNotInPlaylist() if playlistId is set")
        public void testGetAvailableTracksPassesOnTracksFromTrackDAOGetTracksNotInPlaylistIfPlaylistIdIsSet() {
            // Assert
            var expectedTracks = TRACKS;

            Mockito.when(mockedTrackDAO.getTracksNotInPlaylist(TOKEN, PLAYLIST_ID)).thenReturn(TRACKS);

            // Act
            var actual = sut.getAvailableTracks(TOKEN, PLAYLIST_ID);
            var actualTracks = actual.getTracks();

            // Assert
            assertEquals(expectedTracks, actualTracks);
        }

        @Test
        @DisplayName("Test getAvailableTracks() calls trackDAO.getAllTracks() if playlistId isn't set")
        public void testGetAvailableTracksCallsTrackDAOGetAllTracksIfPlaylistIdIsNotSet() {
            // Arrange

            // Act
            sut.getAvailableTracks(TOKEN, EMPTY_PLAYLIST_ID);

            // Assert
            Mockito.verify(mockedTrackDAO).getAllTracks(TOKEN);
        }

        @Test
        @DisplayName("Test getAvailableTracks() passes on tracks from trackDAO.getAllTracks() if playlistId isn't set")
        public void testGetAvailableTracksPassesOnTracksFromTrackDAOGetAllTracksIfPlaylistIdIsNotSet() {
            // Assert
            var expectedTracks = TRACKS;

            Mockito.when(mockedTrackDAO.getAllTracks(TOKEN)).thenReturn(TRACKS);

            // Act
            var actual = sut.getAvailableTracks(TOKEN, EMPTY_PLAYLIST_ID);
            var actualTracks = actual.getTracks();

            // Assert
            assertEquals(expectedTracks, actualTracks);
        }
    }
}
