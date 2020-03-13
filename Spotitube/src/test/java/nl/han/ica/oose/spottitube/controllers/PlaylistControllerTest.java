package nl.han.ica.oose.spottitube.controllers;

import han.oose.dea.spottitube.controllers.PlaylistController;
import han.oose.dea.spottitube.controllers.dto.*;
import han.oose.dea.spottitube.controllers.service.LoginService;
import han.oose.dea.spottitube.controllers.service.PlaylistService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.sound.midi.Track;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@DisplayName("PlaylistController unit tests")
public class PlaylistControllerTest {

    private PlaylistController sut;
    private LoginService mockedLoginService;
    private PlaylistService mockedPlaylistService;

    private static final String TOKEN = "1234-1234-1234";
    private static final int PLAYLIST_ID = 1;

    private static final int HTTP_OK = 200;
    private static final int HTTP_UNAUTHORIZED = 401;

    @BeforeEach
    public void setup() {
        sut = new PlaylistController();

        mockedLoginService = Mockito.mock(LoginService.class);
        sut.setLoginService(mockedLoginService);

        mockedPlaylistService = Mockito.mock(PlaylistService.class);
        sut.setPlaylistService(mockedPlaylistService);
    }

    @Test
    @DisplayName("Test getAllPlaylists returns unautherized if token doesn't match")
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
    @DisplayName("Test getAllPlaylists passes on playlists if token matches")
    public void testGetAllPlaylistsPassesOnPlaylistsIfTokenMatches() {
        // Arrange
        var tracks = new ArrayList<TrackDTO>();
            tracks.add(new TrackDTO(3, "Ocean and a rock", "Lisa Hannigan", 337, "Sea sew", 0, "undefined", "undefined", false));
            tracks.add(new TrackDTO(4, "So Long, Marianne", "Leonard Cohen", 546, "Songs of Leonard Cohen", 0, "undefined", "undefined", false));
            tracks.add(new TrackDTO(5, "One", "Metallica", 423, "undefined", 37, "18-03-2001", "Long version", true));
        var playlists = new ArrayList<PlaylistDTO>();
            playlists.add(new PlaylistDTO(1, "Death metal", true, tracks));
            playlists.add(new PlaylistDTO(2, "Pop", false, tracks));
            playlists.add(new PlaylistDTO(3, "Progressive Rock", true, tracks));
        var playlistsDTO = new PlaylistsDTO(playlists, 1234);

        Mockito.when(mockedLoginService.doesTokenMatch(TOKEN)).thenReturn(true);
        Mockito.when(mockedPlaylistService.getAllPlaylists()).thenReturn(playlistsDTO);

        var expectedStatus = HTTP_OK;
        var expectedEntity = playlistsDTO;

        // Act
        var response = sut.getAllPlaylists(TOKEN);

        // Assert
        assertEquals(expectedStatus, response.getStatus());
        assertEquals(expectedEntity, response.getEntity());
    }

    @Test
    @DisplayName("Test getPlaylistsTracks returns unautherized if token doesn't match")
    public void testGetPlaylistsTracksReturnsUnauthorizedIfTokenDoesntMatch() {
        // Arrange
        Mockito.when(mockedLoginService.doesTokenMatch(TOKEN)).thenReturn(false);
        var playlistId = PLAYLIST_ID;
        var expectedStatus = HTTP_UNAUTHORIZED;

        // Act
        var response = sut.getPlaylistsTracks(TOKEN, playlistId);

        // Assert
        assertEquals(expectedStatus, response.getStatus());
        assertNull(response.getEntity());
    }

    @Test
    @DisplayName("Test getPlaylistTracks passes on tracks if token matches")
    public void testGetPlaylistTracksPassesOnTracksIfTokenMatches() {
        // Arrange
        var tracks = new ArrayList<TrackDTO>();
        tracks.add(new TrackDTO(3, "Ocean and a rock", "Lisa Hannigan", 337, "Sea sew", 0, "undefined", "undefined", false));
        tracks.add(new TrackDTO(4, "So Long, Marianne", "Leonard Cohen", 546, "Songs of Leonard Cohen", 0, "undefined", "undefined", false));
        tracks.add(new TrackDTO(5, "One", "Metallica", 423, "undefined", 37, "18-03-2001", "Long version", true));

        var tracksDTO = new TracksDTO(tracks);

        Mockito.when(mockedLoginService.doesTokenMatch(TOKEN)).thenReturn(true);
        Mockito.when(mockedPlaylistService.getPlaylistsTracks(PLAYLIST_ID)).thenReturn(tracksDTO);

        var expectedStatus = HTTP_OK;
        var expectedEntity = tracksDTO;

        // Act
        var response = sut.getPlaylistsTracks(TOKEN, PLAYLIST_ID);

        // Assert
        assertEquals(expectedStatus, response.getStatus());
        assertEquals(expectedEntity, response.getEntity());
    }
}
