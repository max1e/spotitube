package han.oose.dea.spotitube.controllers;

import han.oose.dea.spotitube.controllers.dto.*;
import han.oose.dea.spotitube.controllers.service.LoginService;
import han.oose.dea.spotitube.controllers.service.PlaylistService;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("PlaylistController unit tests")
public class PlaylistControllerTest {

    private PlaylistController sut;
    private LoginService mockedLoginService;
    private PlaylistService mockedPlaylistService;

    private static final String TOKEN = "1234-1234-1234";
    private static final int PLAYLIST_ID = 1;
    private static final int TRACK_ID = 1;

    private static final TrackDTO TRACK_DTO = new TrackDTO();
    private static final TracksDTO TRACKS_DTO = new TracksDTO();
    private static final PlaylistDTO PLAYLIST_DTO = new PlaylistDTO();
    private static final PlaylistsDTO PLAYLISTS_DTO = new PlaylistsDTO();

    @BeforeAll
    static void variableInit() {
        PLAYLIST_DTO.setId(PLAYLIST_ID);
    }

    @BeforeEach
    public void setup() {
        sut = new PlaylistController();

        mockedLoginService = Mockito.mock(LoginService.class);
        sut.setLoginService(mockedLoginService);

        mockedPlaylistService = Mockito.mock(PlaylistService.class);
        sut.setPlaylistService(mockedPlaylistService);
    }

    @Nested
    @DisplayName("getAllPlaylists() unit tests")
    class GetAllPlaylistsTest {
        @Test
        @DisplayName("Test getAllPlaylists() passes on playlists")
        public void testGetAllPlaylistsPassesOnPlaylists() {
            // Arrange
            Mockito.when(mockedLoginService.validateToken(TOKEN)).thenReturn(true);
            Mockito.when(mockedPlaylistService.getAllPlaylists(TOKEN)).thenReturn(PLAYLISTS_DTO);

            var expectedStatus = Response.Status.OK.getStatusCode();
            var expectedEntity = PLAYLISTS_DTO;

            // Act
            var response = sut.getAllPlaylists(TOKEN);

            var actualStatus = response.getStatus();
            var actualEntity = response.getEntity();

            // Assert
            assertEquals(expectedStatus, actualStatus);
            assertEquals(expectedEntity, actualEntity);
        }
    }

    @Nested
    @DisplayName("getPlaylistsTracks() unit tests")
    class GetPlaylistsTracksTest {
        @Test
        @DisplayName("Test getPlaylistsTracks() passes on tracks")
        public void testGetPlaylistsTracksPassesOnTracks() {
            // Arrange
            Mockito.when(mockedLoginService.validateToken(TOKEN)).thenReturn(true);
            Mockito.when(mockedPlaylistService.getPlaylistsTracks(TOKEN, PLAYLIST_ID)).thenReturn(TRACKS_DTO);

            var expectedStatus = Response.Status.OK.getStatusCode();
            var expectedEntity = TRACKS_DTO;

            // Act
            var response = sut.getPlaylistsTracks(TOKEN, PLAYLIST_ID);

            var actualStatus = response.getStatus();
            var actualEntity = response.getEntity();

            // Assert
            assertEquals(expectedStatus, actualStatus);
            assertEquals(expectedEntity, actualEntity);
        }
    }


    @Nested
    @DisplayName("addPlaylist unit tests")
    class AddPlaylistTest {
        @Test
        @DisplayName("Test addPlaylist() calls playlistService.addPlaylist()")
        public void testAddPlaylistCallsPlaylistServiceAddPlaylist() {
            // Arrange
            Mockito.when(mockedLoginService.validateToken(TOKEN)).thenReturn(true);

            // Act
            sut.addPlaylist(TOKEN, PLAYLIST_DTO);

            // Assert
            Mockito.verify(mockedPlaylistService).addPlaylist(TOKEN, PLAYLIST_DTO);
        }

        @Test
        @DisplayName("Test addPlaylist() passes on playlists")
        public void testAddPlaylistPassesOnPlaylists() {
            // Arrange
            Mockito.when(mockedLoginService.validateToken(TOKEN)).thenReturn(true);
            Mockito.when(mockedPlaylistService.getAllPlaylists(TOKEN)).thenReturn(PLAYLISTS_DTO);

            var expectedStatus = Response.Status.CREATED.getStatusCode();
            var expectedEntity = PLAYLISTS_DTO;

            // Act
            var response = sut.addPlaylist(TOKEN, PLAYLIST_DTO);

            var actualStatus = response.getStatus();
            var actualEntity = response.getEntity();

            // Assert
            assertEquals(expectedStatus, actualStatus);
            assertEquals(expectedEntity, actualEntity);
        }
    }

    @Nested
    @DisplayName("editPlaylistName() unit tests")
    class EditPlaylistNameTest {
        @Test
        @DisplayName("Test editPlaylistName() calls playlistService.editPlaylistName()")
        public void testEditPlaylistNameCallsPlaylistServiceEditPlaylistName() {
            // Arrange
            Mockito.when(mockedLoginService.validateToken(TOKEN)).thenReturn(true);

            // Act
            sut.editPlaylistName(TOKEN, PLAYLIST_ID, PLAYLIST_DTO);

            // Assert
            Mockito.verify(mockedPlaylistService).editPlaylistName(TOKEN, PLAYLIST_ID, PLAYLIST_DTO);
        }

        @Test
        @DisplayName("Test editPlaylistName() passes on playlists")
        public void testEditPlaylistNamePassesOnPlaylists() {
            // Arrange
            Mockito.when(mockedLoginService.validateToken(TOKEN)).thenReturn(true);
            Mockito.when(mockedPlaylistService.getAllPlaylists(TOKEN)).thenReturn(PLAYLISTS_DTO);

            var expectedStatus = Response.Status.OK.getStatusCode();
            var expectedEntity = PLAYLISTS_DTO;

            // Act
            var response = sut.editPlaylistName(TOKEN, PLAYLIST_ID, PLAYLIST_DTO);

            var actualStatus = response.getStatus();
            var actualEntity = response.getEntity();

            // Assert
            assertEquals(expectedStatus, actualStatus);
            assertEquals(expectedEntity, actualEntity);
        }
    }

    @Nested
    @DisplayName("deletePlaylist() unit tests")
    class DeletePlaylistTest {
        @Test
        @DisplayName("Test deletePlaylist() calls playlistService.deletePlaylist()")
        public void testDeletePlaylistCallsPlaylistServiceDeletePlaylist() {
            // Arrange
            Mockito.when(mockedLoginService.validateToken(TOKEN)).thenReturn(true);

            // Act
            sut.deletePlaylist(TOKEN, PLAYLIST_ID);

            // Assert
            Mockito.verify(mockedPlaylistService).deletePlaylist(TOKEN, PLAYLIST_ID);
        }

        @Test
        @DisplayName("Test deletePlaylist() passes on playlists")
        public void testDeletePlaylistPassesOnPlaylists() {
            // Arrange
            Mockito.when(mockedLoginService.validateToken(TOKEN)).thenReturn(true);
            Mockito.when(mockedPlaylistService.getAllPlaylists(TOKEN)).thenReturn(PLAYLISTS_DTO);

            var expectedStatus = Response.Status.OK.getStatusCode();
            var expectedEntity = PLAYLISTS_DTO;

            // Act
            var response = sut.deletePlaylist(TOKEN, PLAYLIST_ID);

            var actualStatus = response.getStatus();
            var actualEntity = response.getEntity();

            // Assert
            assertEquals(expectedStatus, actualStatus);
            assertEquals(expectedEntity, actualEntity);
        }
    }

    @Nested
    @DisplayName("removeTrackFromPlaylist() unit tests")
    class RemoveTrackFromPlaylistTest {
        @Test
        @DisplayName("Test removeTrackFromPlaylist() calls playlistService.removeTrackFromPlaylist()")
        public void testRemoveTrackFromPlaylistCallsPlaylistServiceRemoveTrackFromPlaylist() {
            // Arrange
            Mockito.when(mockedLoginService.validateToken(TOKEN)).thenReturn(true);

            // Act
            sut.removeTrackFromPlaylist(TOKEN, PLAYLIST_ID, TRACK_ID);

            // Assert
            Mockito.verify(mockedPlaylistService).removeTrackFromPlaylist(TOKEN, PLAYLIST_ID, TRACK_ID);
        }

        @Test
        @DisplayName("Test removeTrackFromPlaylist() passes on tracks")
        public void testRemoveTrackFromPlaylistPassesOnTracks() {
            // Arrange
            Mockito.when(mockedLoginService.validateToken(TOKEN)).thenReturn(true);
            Mockito.when(mockedPlaylistService.getPlaylistsTracks(TOKEN, PLAYLIST_ID)).thenReturn(TRACKS_DTO);

            var expectedStatus = Response.Status.OK.getStatusCode();
            var expectedEntity = TRACKS_DTO;

            // Act
            var response = sut.removeTrackFromPlaylist(TOKEN, PLAYLIST_ID, TRACK_ID);

            var actualStatus = response.getStatus();
            var actualEntity = response.getEntity();

            // Assert
            assertEquals(expectedStatus, actualStatus);
            assertEquals(expectedEntity, actualEntity);
        }
    }

    @Nested
    @DisplayName("addTrackToPlaylist() unit tests")
    class AddTrackToPlaylistTest {
        @Test
        @DisplayName("Test addTrackToPlaylist() calls playlistService.addTrackToPlaylist()")
        public void testAddTrackToPlaylistCallsPlaylistServiceRemoveAddTrackToPlaylist() {
            // Arrange
            Mockito.when(mockedLoginService.validateToken(TOKEN)).thenReturn(true);

            // Act
            sut.addTrackToPlaylist(TOKEN, PLAYLIST_ID, TRACK_DTO);

            // Assert
            Mockito.verify(mockedPlaylistService).addTrackToPlaylist(TOKEN, PLAYLIST_ID, TRACK_DTO);
        }

        @Test
        @DisplayName("Test addTrackToPlaylist() passes on tracks")
        public void testAddTrackToPlaylistPassesOnTracks() {
            // Arrange
            Mockito.when(mockedLoginService.validateToken(TOKEN)).thenReturn(true);
            Mockito.when(mockedPlaylistService.getPlaylistsTracks(TOKEN, PLAYLIST_ID)).thenReturn(TRACKS_DTO);

            var expectedStatus = Response.Status.OK.getStatusCode();
            var expectedEntity = TRACKS_DTO;

            // Act
            var response = sut.addTrackToPlaylist(TOKEN, PLAYLIST_ID, TRACK_DTO);

            var actualStatus = response.getStatus();
            var actualEntity = response.getEntity();

            // Assert
            assertEquals(expectedStatus, actualStatus);
            assertEquals(expectedEntity, actualEntity);
        }
    }
}
