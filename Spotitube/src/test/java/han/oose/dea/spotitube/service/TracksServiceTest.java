package han.oose.dea.spotitube.service;

import han.oose.dea.spotitube.controllers.dto.TrackDTO;
import han.oose.dea.spotitube.service.datasource.TracksDAO;
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
    private TracksDAO mockedTracksDAO;

    private static final int PLAYLIST_ID = 1;
    private static final Integer EMPTY_PLAYLIST_ID = null;

    private static final List<TrackDTO> TRACKS = new ArrayList<>();

    @BeforeEach
    public void setup() {
        sut = new TracksServiceImpl();

        mockedTracksDAO = Mockito.mock(TracksDAO.class);
        sut.setTracksDAO(mockedTracksDAO);
    }

    @Nested
    @DisplayName("getAvailableTracks() unit tests")
    class GetAvailableTracksTest {
        @Test
        @DisplayName("Test getAvailableTracks() calls playlistDAO.getTracksNotInPlaylist() if playlistId is set")
        public void testGetAvailableTracksCallsPlaylistDAOGetTracksNotInPlaylistIfPlaylistIdIsSet() {
            // Arrange

            // Act
            sut.getAvailableTracks(PLAYLIST_ID);

            // Assert
            Mockito.verify(mockedTracksDAO).getTracksNotInPlaylist(PLAYLIST_ID);
        }

        @Test
        @DisplayName("Test getAvailableTracks() passes on tracks from playlistDAO.getTracksNotInPlaylist() if playlistId is set")
        public void testGetAvailableTracksPassesOnTracksFromPlaylistDAOGetTracksNotInPlaylistIfPlaylistIdIsSet() {
            // Assert
            var expectedTracks = TRACKS;

            Mockito.when(mockedTracksDAO.getTracksNotInPlaylist(PLAYLIST_ID)).thenReturn(TRACKS);

            // Act
            var actual = sut.getAvailableTracks(PLAYLIST_ID);
            var actualTracks = actual.getTracks();

            // Assert
            assertEquals(expectedTracks, actualTracks);
        }

        @Test
        @DisplayName("Test getAvailableTracks() calls playlistDAO.getAllTracks() if playlistId isn't set")
        public void testGetAvailableTracksCallsPlaylistDAOGetAllTracksIfPlaylistIdIsNotSet() {
            // Arrange

            // Act
            sut.getAvailableTracks(EMPTY_PLAYLIST_ID);

            // Assert
            Mockito.verify(mockedTracksDAO).getAllTracks();
        }

        @Test
        @DisplayName("Test getAvailableTracks() passes on tracks from playlistDAO.getAllTracks() if playlistId isn't set")
        public void testGetAvailableTracksPassesOnTracksFromPlaylistDAOGetAllTracksIfPlaylistIdIsNotSet() {
            // Assert
            var expectedTracks = TRACKS;

            Mockito.when(mockedTracksDAO.getAllTracks()).thenReturn(TRACKS);

            // Act
            var actual = sut.getAvailableTracks(EMPTY_PLAYLIST_ID);
            var actualTracks = actual.getTracks();

            // Assert
            assertEquals(expectedTracks, actualTracks);
        }
    }
}
