package han.oose.dea.spotitube.service;

import han.oose.dea.spotitube.controllers.dto.PlaylistDTO;
import han.oose.dea.spotitube.controllers.dto.TrackDTO;
import han.oose.dea.spotitube.service.datasource.PlaylistDAO;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import javax.ws.rs.BadRequestException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;

@DisplayName("PlaylistService unit tests")
public class PlaylistServiceTest {

    private PlaylistServiceImpl sut;
    private PlaylistDAO mockedPlaylistDAO;

    private static final int PLAYLIST_ID = 1;
    private static final String PLAYLIST_NAME = "playlist";
    private static final int TRACK_ID = 1;
    private static final String TOKEN = "1234-1234-1234";
    private static final int DURATION = 674;

    private static final PlaylistDTO PLAYLIST_DTO = new PlaylistDTO();
    private static final List<PlaylistDTO> PLAYLIST_DTO_LIST = new ArrayList<>();
    private static final TrackDTO TRACK_DTO = new TrackDTO(3, "Ocean and a rock", "Lisa Hannigan", 337, "Sea sew", 0, "undefined", "undefined", false);
    private static final List<TrackDTO> TRACKS = new ArrayList<>();

    @BeforeAll
    public static void variableInit() {
        TRACKS.add(new TrackDTO(3, "Ocean and a rock", "Lisa Hannigan", 337, "Sea sew", 0, "undefined", "undefined", false));
        TRACKS.add(new TrackDTO(4, "So Long, Marianne", "Leonard Cohen", 546, "Songs of Leonard Cohen", 0, "undefined", "undefined", false));
        TRACKS.add(new TrackDTO(5, "One", "Metallica", 423, "undefined", 37, "18-03-2001", "Long version", true));
        TRACK_DTO.setId(TRACK_ID);


        var playlistTracks = new ArrayList<TrackDTO>();
        playlistTracks.add(TRACK_DTO);
        playlistTracks.add(TRACK_DTO);
        PLAYLIST_DTO.setTracks(playlistTracks);
        PLAYLIST_DTO.setName(PLAYLIST_NAME);
        PLAYLIST_DTO.setDuration(DURATION);

        PLAYLIST_DTO_LIST.add(PLAYLIST_DTO);
    }

    @BeforeEach
    public void setup() {
        sut = new PlaylistServiceImpl();

        mockedPlaylistDAO = Mockito.mock(PlaylistDAO.class);
        sut.setPlaylistDAO(mockedPlaylistDAO);
    }

    @Nested
    @DisplayName("getAllPlaylists() unit tests")
    class GetAllPlaylistsTest {
        @Test
        @DisplayName("Test getAllPlaylists() calls playlistDAO.getAllPlaylists()")
        public void testGetAllPlaylistsCallsPlaylistDAOGetAllPlaylists() {
            // Arrange

            // Act
            sut.getAllPlaylists(TOKEN);

            // Assert
            Mockito.verify(mockedPlaylistDAO).getAllPlaylists(TOKEN);
        }

        @Test
        @DisplayName("Test getAllPlaylists() passes on playlists from playlistDAO.getAllPlaylists()")
        public void getGetAllPlaylistsPassesOnPlaylistsFromPlaylistDAOGetAllPlaylists() {
            // Assert
            var expectedPlaylists = PLAYLIST_DTO_LIST;

            Mockito.when(mockedPlaylistDAO.getAllPlaylists(TOKEN)).thenReturn(PLAYLIST_DTO_LIST);

            // Act
            var actual = sut.getAllPlaylists(TOKEN);
            var actualPlaylists = actual.getPlaylists();

            // Assert
            assertEquals(expectedPlaylists, actualPlaylists);
        }

        @Test
        @DisplayName("Test getAllPlaylists() adds correct duration to playlists")
        public void getGetAllPlaylistsAddsCorrectDurationToPlaylists() {
            // Assert
            var expectedDuration = DURATION;

            Mockito.when(mockedPlaylistDAO.getAllPlaylists(TOKEN)).thenReturn(PLAYLIST_DTO_LIST);

            // Act
            var actual = sut.getAllPlaylists(TOKEN);
            var actualDuration = actual.getLength();

            // Assert
            assertEquals(expectedDuration, actualDuration);
        }
    }

    @Nested
    @DisplayName("getPlaylistsTracks() unit tests")
    class GetPlaylistTracksTest {
        @Test
        @DisplayName("Test getPlaylistsTracks() calls playlistDAO.getPlaylistsTracks()")
        public void testGetPlaylistsTracksCallsPlaylistDAOGetPlaylistsTracks() {
            // Arrange

            // Act
            sut.getPlaylistsTracks(PLAYLIST_ID);

            // Assert
            Mockito.verify(mockedPlaylistDAO).getPlaylistsTracks(PLAYLIST_ID);
        }

        @Test
        @DisplayName("Test getPlaylistsTracks() passes on tracks from playlistDAO.getPlaylistsTracks()")
        public void getGetPlaylistsTracksPassesOnTracksFromPlaylistDAOGetPlaylistsTracks() {
            // Assert
            var expectedTracks = TRACKS;

            Mockito.when(mockedPlaylistDAO.getPlaylistsTracks(PLAYLIST_ID)).thenReturn(TRACKS);

            // Act
            var actual = sut.getPlaylistsTracks(PLAYLIST_ID);
            var actualTracks = actual.getTracks();

            // Assert
            assertEquals(expectedTracks, actualTracks);
        }
    }

    @Nested
    @DisplayName("deletePlaylist() unit tests")
    class DeletePlaylistTest {

        @Test
        @DisplayName("Test deletePlaylist() calls playlistDAO.deletePlaylist()")
        public void testDeletePlaylistCallsPlaylistDAODeletePlaylist() {
            // Arrange

            // Act
            sut.deletePlaylist(TOKEN, PLAYLIST_ID);

            // Assert
            Mockito.verify(mockedPlaylistDAO).deletePlaylist(TOKEN, PLAYLIST_ID);
        }
    }

    @Nested
    @DisplayName("addPlaylist() unit tests")
    class AddPlaylistTest {

        @Test
        @DisplayName("Test addPlaylist() calls playlistDAO.addPlaylist()")
        public void testAddPlaylistCallsPlaylistDAOAddPlaylist() {
            // Arrange
            var expectedPlaylistName = PLAYLIST_NAME;

            // Act
            sut.addPlaylist(TOKEN, PLAYLIST_DTO);

            // Assert
            Mockito.verify(mockedPlaylistDAO).addPlaylist(TOKEN, expectedPlaylistName);
        }

        @Test
        @DisplayName("Test addPlaylist() calls playlistDAO.addTrackToPlaylist()")
        public void testAddPlaylistCallsPlaylistDAOAddTrackToPlaylist() {
            // Arrange
            var expectedPlaylistId = PLAYLIST_ID;
            var expectedTrackId = 1;

            Mockito.when(mockedPlaylistDAO.addPlaylist(TOKEN, PLAYLIST_NAME)).thenReturn(PLAYLIST_ID);

            // Act
            sut.addPlaylist(TOKEN, PLAYLIST_DTO);

            // Assert
            Mockito.verify(mockedPlaylistDAO, Mockito.times(2)).addTrackToPlaylist(TOKEN, expectedPlaylistId, expectedTrackId);
        }
    }

    @Nested
    @DisplayName("editPlaylistName() unit tests")
    class EditPlaylistNameTest {
        @Test
        @DisplayName("Test editPlaylistName() throws BadRequestException if playlistId does not match playlistDTO")
        public void testEditPlaylistNameThrowsBadRequestExceptionIfPlaylistIdDoesntMatch() {
            // Arrange
            var testPlaylistId = 2;

            // Act & Assert
            assertThrows(BadRequestException.class, () -> sut.editPlaylistName(TOKEN, testPlaylistId, PLAYLIST_DTO));
        }

        @Test
        @DisplayName("Test editPlaylistName() doesn't call playlistDAO.editPlaylistName() if bad request exception is thrown")
        public void testEditPlaylistNameDoesntCallEditPlaylistNameIfBadRequestExceptionIsThrown() {
            // Arrange
            var differentPlaylistId = 2;
            var expectedPlaylistId = PLAYLIST_DTO.getId();
            var expectedNewName = PLAYLIST_DTO.getName();

            // Act & Assert
            try {
                sut.editPlaylistName(TOKEN, differentPlaylistId, PLAYLIST_DTO);
            } catch (BadRequestException e) {
                Mockito.verify(mockedPlaylistDAO, never()).editPlaylistName(TOKEN, expectedPlaylistId, expectedNewName);
            }
        }

        @Test
        @DisplayName("Test editPlaylistName() calls playlistDAO.editPlaylistName()")
        public void testEditPlaylistNameCallsPlaylistDAOEditPlaylistName() {
            // Arrange
            var expectedId = PLAYLIST_DTO.getId();
            var expectedName = PLAYLIST_DTO.getName();

            // Act
            sut.editPlaylistName(TOKEN, expectedId, PLAYLIST_DTO);

            // Assert
            Mockito.verify(mockedPlaylistDAO).editPlaylistName(TOKEN, expectedId, expectedName);
        }
    }

    @Nested
    @DisplayName("removeTrackFromPlaylist() unit tests")
    class RemoveTrackFromPlaylistTest {

        @Test
        @DisplayName("Test removeTrackFromPlaylist() calls playlistDAO.removeTrackFromPlaylist()")
        public void testRemoveTrackFromPlaylistCallsPlaylistDAORemoveTrackFromPlaylist() {
            // Arrange

            // Act
            sut.removeTrackFromPlaylist(TOKEN, PLAYLIST_ID, TRACK_ID);

            // Assert
            Mockito.verify(mockedPlaylistDAO).removeTrackFromPlaylist(TOKEN, PLAYLIST_ID, TRACK_ID);
        }

    }

    @Nested
    @DisplayName("addTrackToPlaylist() unit tests")
    class AddTrackToPlaylistTest {

        @Test
        @DisplayName("Test addTrackToPlaylist() calls playlistDAO.addTrackToPlaylist()")
        public void testAddTrackToPlaylistCallsPlaylistDAOAddTrackToPlaylist() {
            // Arrange
            var expectedTrackId = TRACK_DTO.getId();

            // Act
            sut.addTrackToPlaylist(TOKEN, PLAYLIST_ID, TRACK_DTO);

            // Assert
            Mockito.verify(mockedPlaylistDAO).addTrackToPlaylist(TOKEN, PLAYLIST_ID, expectedTrackId);
        }
    }
}
