package han.oose.dea.spottitube.controllers;

import han.oose.dea.spottitube.controllers.dto.*;
import han.oose.dea.spottitube.controllers.service.LoginService;
import han.oose.dea.spottitube.controllers.service.PlaylistService;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import javax.ws.rs.BadRequestException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.never;

@DisplayName("PlaylistController unit tests")
public class PlaylistControllerTest {

    private PlaylistController sut;
    private LoginService mockedLoginService;
    private PlaylistService mockedPlaylistService;

    private static final String TOKEN = "1234-1234-1234";
    private static final int PLAYLIST_ID = 1;
    private static final int TRACK_ID = 1;

    private static final int HTTP_OK = 200;
    private static final int HTTP_CREATED = 201;
    private static final int HTTP_UNAUTHORIZED = 401;

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
        @DisplayName("Test getAllPlaylists() returns unauthorized if token doesn't match")
        public void testGetAllPlaylistsReturnsUnauthorizedIfTokenDoesntMatch() {
            // Arrange
            Mockito.when(mockedLoginService.doesTokenMatch(TOKEN)).thenReturn(false);

            var expectedStatus = HTTP_UNAUTHORIZED;

            // Act
            var response = sut.getAllPlaylists(TOKEN);

            // Assert
            assertEquals(expectedStatus, response.getStatus());
            assertNull(response.getEntity());
        }

        @Test
        @DisplayName("Test getAllPlaylists() passes on playlists if token matches")
        public void testGetAllPlaylistsPassesOnPlaylistsIfTokenMatches() {
            // Arrange
            Mockito.when(mockedLoginService.doesTokenMatch(TOKEN)).thenReturn(true);
            Mockito.when(mockedPlaylistService.getAllPlaylists()).thenReturn(PLAYLISTS_DTO);

            var expectedStatus = HTTP_OK;
            var expectedEntity = PLAYLISTS_DTO;

            // Act
            var response = sut.getAllPlaylists(TOKEN);

            // Assert
            assertEquals(expectedStatus, response.getStatus());
            assertEquals(expectedEntity, response.getEntity());
        }
    }


    @Nested
    @DisplayName("getPlaylistsTracks() unit tests")
    class GetPlaylistsTracksTest {
        @Test
        @DisplayName("Test getPlaylistsTracks() returns unauthorized if token doesn't match")
        public void testGetPlaylistsTracksReturnsUnauthorizedIfTokenDoesntMatch() {
            // Arrange
            Mockito.when(mockedLoginService.doesTokenMatch(TOKEN)).thenReturn(false);
            var expectedStatus = HTTP_UNAUTHORIZED;

            // Act
            var response = sut.getPlaylistsTracks(TOKEN, PLAYLIST_ID);

            // Assert
            assertEquals(expectedStatus, response.getStatus());
            assertNull(response.getEntity());
        }

        @Test
        @DisplayName("Test getPlaylistsTracks() passes on tracks if token matches")
        public void testGetPlaylistsTracksPassesOnTracksIfTokenMatches() {
            // Arrange
            Mockito.when(mockedLoginService.doesTokenMatch(TOKEN)).thenReturn(true);
            Mockito.when(mockedPlaylistService.getPlaylistsTracks(PLAYLIST_ID)).thenReturn(TRACKS_DTO);

            var expectedStatus = HTTP_OK;
            var expectedEntity = TRACKS_DTO;

            // Act
            var response = sut.getPlaylistsTracks(TOKEN, PLAYLIST_ID);

            // Assert
            assertEquals(expectedStatus, response.getStatus());
            assertEquals(expectedEntity, response.getEntity());
        }
    }


    @Nested
    @DisplayName("addPlaylist unit tests")
    class AddPlaylistTest {
        @Test
        @DisplayName("Test addPlaylist() returns unautherized if token doesn't match")
        public void testAddPlaylistReturnsUnauthorizedIfTokenDoesntMatch() {
            // Arrange
            Mockito.when(mockedLoginService.doesTokenMatch(TOKEN)).thenReturn(false);
            var playlistId = PLAYLIST_ID;
            var expectedStatus = HTTP_UNAUTHORIZED;

            // Act
            var response = sut.addPlaylist(TOKEN, PLAYLIST_DTO);

            // Assert
            assertEquals(expectedStatus, response.getStatus());
            assertNull(response.getEntity());
        }

        @Test
        @DisplayName("Test addPlaylist() doesn't call playlistservice.addPlaylist() if token doesn't match")
        public void testAddPlaylistDoesntCallPlaylistServiceAddPlaylistIfTokenDoesntMatch() {
            // Arrange
            Mockito.when(mockedLoginService.doesTokenMatch(TOKEN)).thenReturn(false);

            // Act
            sut.addPlaylist(TOKEN, PLAYLIST_DTO);

            // Assert
            Mockito.verify(mockedPlaylistService, never()).addPlaylist(PLAYLIST_DTO);
        }

        @Test
        @DisplayName("Test addPlaylist() calls playlistservice.addPlaylist() if token matches")
        public void testAddPlaylistCallsPlaylistServiceAddPlaylistIfTokenMatches() {
            // Arrange
            Mockito.when(mockedLoginService.doesTokenMatch(TOKEN)).thenReturn(true);

            // Act
            sut.addPlaylist(TOKEN, PLAYLIST_DTO);

            // Assert
            Mockito.verify(mockedPlaylistService).addPlaylist(PLAYLIST_DTO);
        }

        @Test
        @DisplayName("Test addPlaylist() passes on playlists if token matches")
        public void testAddPlaylistPassesOnPlaylistsIfTokenMatches() {
            // Arrange
            Mockito.when(mockedLoginService.doesTokenMatch(TOKEN)).thenReturn(true);
            Mockito.when(mockedPlaylistService.getAllPlaylists()).thenReturn(PLAYLISTS_DTO);

            var expectedStatus = HTTP_CREATED;
            var expectedEntity = PLAYLISTS_DTO;

            // Act
            var response = sut.addPlaylist(TOKEN, PLAYLIST_DTO);

            // Assert
            assertEquals(expectedStatus, response.getStatus());
            assertEquals(expectedEntity, response.getEntity());
        }
    }

    @Nested
    @DisplayName("editPlaylistName() unit tests")
    class EditPlaylistNameTest {
        @Test
        @DisplayName("Test editPlaylistName() returns unauthorized if token doesn't match")
        public void testEditPlaylistNameReturnsUnauthorizedIfTokenDoesntMatch() {
            // Arrange
            Mockito.when(mockedLoginService.doesTokenMatch(TOKEN)).thenReturn(false);

            var expectedStatus = HTTP_UNAUTHORIZED;

            // Act
            var response = sut.editPlaylistName(TOKEN, PLAYLIST_ID, PLAYLIST_DTO);

            // Assert
            assertEquals(expectedStatus, response.getStatus());
            assertNull(response.getEntity());
        }

        @Test
        @DisplayName("Test editPlaylistName() doesn't call playlistservice.editPlaylistName() if token doesn't match")
        public void testEditPlaylistNameDoesntCallPlaylistServiceEditPlaylistNameIfTokenDoesntMatch() {
            // Arrange
            Mockito.when(mockedLoginService.doesTokenMatch(TOKEN)).thenReturn(false);

            // Act
            sut.editPlaylistName(TOKEN, PLAYLIST_ID, PLAYLIST_DTO);

            // Assert
            Mockito.verify(mockedPlaylistService, never()).editPlaylistName(PLAYLIST_DTO);
        }

        @Test
        @DisplayName("Test editPlaylistName() calls playlistService.editPlaylistName() if token matches")
        public void testEditPlaylistNameCallsPlaylistServiceEditPlaylistNameIfTokenMatches() {
            // Arrange
            Mockito.when(mockedLoginService.doesTokenMatch(TOKEN)).thenReturn(true);

            // Act
            sut.editPlaylistName(TOKEN, PLAYLIST_ID, PLAYLIST_DTO);

            // Assert
            Mockito.verify(mockedPlaylistService).editPlaylistName(PLAYLIST_DTO);
        }

        @Test
        @DisplayName("Test editPlaylistName() passes on playlists if token matches")
        public void testEditPlaylistNamePassesOnPlaylistsIfTokenMatches() {
            // Arrange
            Mockito.when(mockedLoginService.doesTokenMatch(TOKEN)).thenReturn(true);
            Mockito.when(mockedPlaylistService.getAllPlaylists()).thenReturn(PLAYLISTS_DTO);

            var expectedStatus = HTTP_OK;
            var expectedEntity = PLAYLISTS_DTO;

            // Act
            var response = sut.editPlaylistName(TOKEN, PLAYLIST_ID, PLAYLIST_DTO);

            // Assert
            assertEquals(expectedStatus, response.getStatus());
            assertEquals(expectedEntity, response.getEntity());
        }

        @Test
        @DisplayName("Test editPlaylistName() throws BadRequestException if playlistId does not match playlistDTO")
        public void testEditPlaylistNameThrowsBadRequestExceptionIfPlaylistIdDoesntMatch() {
            // Arrange
            Mockito.when(mockedLoginService.doesTokenMatch(TOKEN)).thenReturn(true);

            var differentPlaylistId = 2;

            // Act & Assert
            assertThrows(BadRequestException.class, () -> sut.editPlaylistName(TOKEN, differentPlaylistId, PLAYLIST_DTO));
        }

        @Test
        @DisplayName("Test editPlaylistName() doesn't call playlistService.editPlaylistName() if bad request exception is thrown")
        public void testEditPlaylistNameDoesntCallEditPlaylistNameIfBadRequestExceptionIsThrown() {
            // Arrange
            Mockito.when(mockedLoginService.doesTokenMatch(TOKEN)).thenReturn(true);

            var differentPlaylistId = 2;

            // Act & Assert
            try {
                sut.editPlaylistName(TOKEN, differentPlaylistId, PLAYLIST_DTO);
            } catch (BadRequestException e) {
                Mockito.verify(mockedPlaylistService, never()).editPlaylistName(PLAYLIST_DTO);
            }
        }
    }

    @Nested
    @DisplayName("deletePlaylist() unit tests")
    class DeletePlaylistTest {
        @Test
        @DisplayName("Test deletePlaylist() returns unauthorized if token doesn't match")
        public void testDeletePlaylistReturnsUnauthorizedIfTokenDoesntMatch() {
            // Arrange
            Mockito.when(mockedLoginService.doesTokenMatch(TOKEN)).thenReturn(false);

            var expectedStatus = HTTP_UNAUTHORIZED;

            // Act
            var response = sut.deletePlaylist(TOKEN, PLAYLIST_ID);

            // Assert
            assertEquals(expectedStatus, response.getStatus());
            assertNull(response.getEntity());
        }

        @Test
        @DisplayName("Test deletePlaylist() doesn't call playlistService.deletePlaylist() if token doesn't match")
        public void testDeletePlaylistDoesntCallPlaylistServiceDeletePlaylistIfTokenDoesntMatch() {
            // Arrange
            Mockito.when(mockedLoginService.doesTokenMatch(TOKEN)).thenReturn(false);

            // Act
            sut.deletePlaylist(TOKEN, PLAYLIST_ID);

            // Assert
            Mockito.verify(mockedPlaylistService, never()).deletePlaylist(PLAYLIST_ID);
        }

        @Test
        @DisplayName("Test deletePlaylist() calls playlistservice.deletePlaylist() if token matches")
        public void testDeletePlaylistCallsPlaylistServiceDeletePlaylistIfTokenMatches() {
            // Arrange
            Mockito.when(mockedLoginService.doesTokenMatch(TOKEN)).thenReturn(true);

            // Act
            sut.deletePlaylist(TOKEN, PLAYLIST_ID);

            // Assert
            Mockito.verify(mockedPlaylistService).deletePlaylist(PLAYLIST_ID);
        }

        @Test
        @DisplayName("Test deletePlaylist() passes on playlists if token matches")
        public void testDeletePlaylistPassesOnPlaylistsIfTokenMatches() {
            // Arrange
            Mockito.when(mockedLoginService.doesTokenMatch(TOKEN)).thenReturn(true);
            Mockito.when(mockedPlaylistService.getAllPlaylists()).thenReturn(PLAYLISTS_DTO);

            var expectedStatus = HTTP_OK;
            var expectedEntity = PLAYLISTS_DTO;

            // Act
            var response = sut.deletePlaylist(TOKEN, PLAYLIST_ID);

            // Assert
            assertEquals(expectedStatus, response.getStatus());
            assertEquals(expectedEntity, response.getEntity());
        }
    }

    @Nested
    @DisplayName("removeTrackFromPlaylist() unit tests")
    class RemoveTrackFromPlaylistTest {
        @Test
        @DisplayName("Test removeTrackFromPlaylist() returns unauthorized if token doesn't match")
        public void testRemoveTrackFromPlaylistReturnsUnauthorizedIfTokenDoesntMatch() {
            // Arrange
            Mockito.when(mockedLoginService.doesTokenMatch(TOKEN)).thenReturn(false);

            var expectedStatus = HTTP_UNAUTHORIZED;

            // Act
            var response = sut.removeTrackFromPlaylist(TOKEN, PLAYLIST_ID, TRACK_ID);

            // Assert
            assertEquals(expectedStatus, response.getStatus());
            assertNull(response.getEntity());
        }

        @Test
        @DisplayName("Test removeTrackFromPlaylist() doesn't call playlistService.removeTrackFromPlaylist() if token doesn't match")
        public void testRemoveTrackFromPlaylistDoesntCallPlaylistServiceRemoveTrackFromPlaylistIfTokenDoesntMatch() {
            // Arrange
            Mockito.when(mockedLoginService.doesTokenMatch(TOKEN)).thenReturn(false);

            // Act
            sut.removeTrackFromPlaylist(TOKEN, PLAYLIST_ID, TRACK_ID);

            // Assert
            Mockito.verify(mockedPlaylistService, never()).removeTrackFromPlaylist(PLAYLIST_ID, TRACK_ID);
        }

        @Test
        @DisplayName("Test removeTrackFromPlaylist() calls playlistservice.removeTrackFromPlaylist() if token matches")
        public void testRemoveTrackFromPlaylistCallsPlaylistServiceRemoveTrackFromPlaylistIfTokenMatches() {
            // Arrange
            Mockito.when(mockedLoginService.doesTokenMatch(TOKEN)).thenReturn(true);

            // Act
            sut.removeTrackFromPlaylist(TOKEN, PLAYLIST_ID, TRACK_ID);

            // Assert
            Mockito.verify(mockedPlaylistService).removeTrackFromPlaylist(PLAYLIST_ID, TRACK_ID);
        }

        @Test
        @DisplayName("Test removeTrackFromPlaylist() passes on tracks if token matches")
        public void testRemoveTrackFromPlaylistPassesOnTracksIfTokenMatches() {
            // Arrange
            Mockito.when(mockedLoginService.doesTokenMatch(TOKEN)).thenReturn(true);
            Mockito.when(mockedPlaylistService.getPlaylistsTracks(PLAYLIST_ID)).thenReturn(TRACKS_DTO);

            var expectedStatus = HTTP_OK;
            var expectedEntity = TRACKS_DTO;

            // Act
            var response = sut.removeTrackFromPlaylist(TOKEN, PLAYLIST_ID, TRACK_ID);

            // Assert
            assertEquals(expectedStatus, response.getStatus());
            assertEquals(expectedEntity, response.getEntity());
        }
    }

    @Nested
    @DisplayName("addTrackToPlaylist() unit tests")
    class AddTrackToPlaylistTest {
        @Test
        @DisplayName("Test addTrackToPlaylist() returns unauthorized if token doesn't match")
        public void testAddTrackToPlaylistReturnsUnauthorizedIfTokenDoesntMatch() {
            // Arrange
            Mockito.when(mockedLoginService.doesTokenMatch(TOKEN)).thenReturn(false);

            var expectedStatus = HTTP_UNAUTHORIZED;

            // Act
            var response = sut.addTrackToPlaylist(TOKEN, PLAYLIST_ID, TRACK_DTO);

            // Assert
            assertEquals(expectedStatus, response.getStatus());
            assertNull(response.getEntity());
        }

        @Test
        @DisplayName("Test addTrackToPlaylist() doesn't call playlistService.addTrackToPlaylist() if token doesn't match")
        public void testAddTrackToPlaylistDoesntCallPlaylistServiceAddTrackToPlaylistIfTokenDoesntMatch() {
            // Arrange
            Mockito.when(mockedLoginService.doesTokenMatch(TOKEN)).thenReturn(false);

            // Act
            sut.addTrackToPlaylist(TOKEN, PLAYLIST_ID, TRACK_DTO);

            // Assert
            Mockito.verify(mockedPlaylistService, never()).addTrackToPlaylist(PLAYLIST_ID, TRACK_DTO);
        }

        @Test
        @DisplayName("Test addTrackToPlaylist() calls playlistservice.addTrackToPlaylist() if token matches")
        public void testAddTrackToPlaylistCallsPlaylistServiceRemoveAddTrackToPlaylistIfTokenMatches() {
            // Arrange
            Mockito.when(mockedLoginService.doesTokenMatch(TOKEN)).thenReturn(true);

            // Act
            sut.addTrackToPlaylist(TOKEN, PLAYLIST_ID, TRACK_DTO);

            // Assert
            Mockito.verify(mockedPlaylistService).addTrackToPlaylist(PLAYLIST_ID, TRACK_DTO);
        }

        @Test
        @DisplayName("Test addTrackToPlaylist() passes on tracks if token matches")
        public void testAddTrackToPlaylistPassesOnTracksIfTokenMatches() {
            // Arrange
            Mockito.when(mockedLoginService.doesTokenMatch(TOKEN)).thenReturn(true);
            Mockito.when(mockedPlaylistService.getPlaylistsTracks(PLAYLIST_ID)).thenReturn(TRACKS_DTO);

            var expectedStatus = HTTP_OK;
            var expectedEntity = TRACKS_DTO;

            // Act
            var response = sut.addTrackToPlaylist(TOKEN, PLAYLIST_ID, TRACK_DTO);

            // Assert
            assertEquals(expectedStatus, response.getStatus());
            assertEquals(expectedEntity, response.getEntity());
        }
    }
}
