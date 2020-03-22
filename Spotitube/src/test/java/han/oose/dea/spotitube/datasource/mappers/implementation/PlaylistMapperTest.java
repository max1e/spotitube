package han.oose.dea.spotitube.datasource.mappers.implementation;

import han.oose.dea.spotitube.controllers.dto.TrackDTO;
import han.oose.dea.spotitube.datasource.mappers.abstraction.PlaylistMapper;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class PlaylistMapperTest {

    private PlaylistMapperImpl sut;
    private ResultSet mockedResultset;

    private static final int PLAYLIST_ID = 1;
    private static final String PLAYLIST_NAME = "playlist";
    private static final boolean IS_OWNER = false;
    private static final List<TrackDTO> TRACKS = null;
    private static final int DURATION = 10;


    @BeforeEach
    public void setup() {
        sut = new PlaylistMapperImpl();

        mockedResultset = Mockito.mock(ResultSet.class);
    }

    @Nested
    @DisplayName("toPlaylistDTOList() unit tests")
    class ToPlaylistDTOListTest {
        @Test
        @DisplayName("Test toPlaylistDTOList() maps resultset")
        public void testToPlaylistDTOListMapsResultset() {
            try {
                // Arrange
                Mockito.when(mockedResultset.next()).thenReturn(true, false);
                Mockito.when(mockedResultset.getInt("playlistId")).thenReturn(PLAYLIST_ID);
                Mockito.when(mockedResultset.getString("playlistName")).thenReturn(PLAYLIST_NAME);
                Mockito.when(mockedResultset.getBoolean("isOwner")).thenReturn(IS_OWNER);

                var expectedPlaylistId = PLAYLIST_ID;
                var expectedPlaylistName = PLAYLIST_NAME;
                var expectedIsOwner = IS_OWNER;
                var expectedTracks = TRACKS;

                // Act
                var playlists = sut.toPlaylistDTOList(mockedResultset);

                var actualPlaylistId = playlists.get(0).getId();
                var actualPlaylistName = playlists.get(0).getName();
                var actualIsOwner = playlists.get(0).getOwner();
                var actualTracks = playlists.get(0).getTracks();

                // Assert
                assertEquals(expectedPlaylistId, actualPlaylistId);
                assertEquals(expectedPlaylistName, actualPlaylistName);
                assertEquals(expectedIsOwner, actualIsOwner);
                assertEquals(expectedTracks, actualTracks);
            } catch (SQLException e) {
                fail();
            }
        }

        @Test
        @DisplayName("Test toPlaylistDTOList() returns the right amount of playlists")
        public void testToPlaylistDTOListReturnsTheRightAmountOfPlaylists() {
            try {
                // Arrange
                Mockito.when(mockedResultset.next()).thenReturn(true, true, false);

                var expectedNPlaylists = 2;

                // Act
                var playlists = sut.toPlaylistDTOWithDurationList(mockedResultset);

                var actualNPlaylists = playlists.size();

                // Assert
                assertEquals(expectedNPlaylists, actualNPlaylists);
            } catch (SQLException e) {
                fail();
            }
        }
    }

    @Nested
    @DisplayName("toPlaylistDTOWithDurationList() unit tests")
    class ToPlaylistDTOWithDurationListTest {
        @Test
        @DisplayName("Test toPlaylistDTOWithDurationList() maps resultset")
        public void testToPlaylistDTOWithDurationListMapsResultset() {
            try {
                // Arrange
                Mockito.when(mockedResultset.next()).thenReturn(true, false);
                Mockito.when(mockedResultset.getInt("playlistId")).thenReturn(PLAYLIST_ID);
                Mockito.when(mockedResultset.getString("playlistName")).thenReturn(PLAYLIST_NAME);
                Mockito.when(mockedResultset.getBoolean("isOwner")).thenReturn(IS_OWNER);
                Mockito.when(mockedResultset.getInt("duration")).thenReturn(DURATION);

                var expectedPlaylistId = PLAYLIST_ID;
                var expectedPlaylistName = PLAYLIST_NAME;
                var expectedIsOwner = IS_OWNER;
                var expectedTracks = TRACKS;
                var expectedDuration = DURATION;

                // Act
                var playlists = sut.toPlaylistDTOWithDurationList(mockedResultset);

                var actualPlaylistId = playlists.get(0).getId();
                var actualPlaylistName = playlists.get(0).getName();
                var actualIsOwner = playlists.get(0).getOwner();
                var actualTracks = playlists.get(0).getTracks();
                var actualDuration = playlists.get(0).getDuration();

                // Assert
                assertEquals(expectedPlaylistId, actualPlaylistId);
                assertEquals(expectedPlaylistName, actualPlaylistName);
                assertEquals(expectedIsOwner, actualIsOwner);
                assertEquals(expectedTracks, actualTracks);
                assertEquals(expectedDuration, actualDuration);
            } catch (SQLException e) {
                fail();
            }
        }

        @Test
        @DisplayName("Test toPlaylistDTOWithDurationList() returns the right amount of playlists")
        public void testToPlaylistDTOWithDurationListReturnsTheRightAmountOfPlaylists() {
            try {
                // Arrange
                Mockito.when(mockedResultset.next()).thenReturn(true, true, false);

                var expectedNPlaylists = 2;

                // Act
                var playlists = sut.toPlaylistDTOList(mockedResultset);

                var actualNPlaylists = playlists.size();

                // Assert
                assertEquals(expectedNPlaylists, actualNPlaylists);
            } catch (SQLException e) {
                fail();
            }
        }
    }
}
