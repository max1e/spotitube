package han.oose.dea.spotitube.datasource.mappers.implementation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TrackMapperTest {
    private TrackMapperImpl sut;
    private ResultSet mockedResultset;

    private static final int TRACK_ID = 1;
    private static final String TITLE = "title";
    private static final String PERFORMER_NAME = "jelmo";
    private static final int DURATION = 500;
    private static final String ALBUM_NAME = "album";
    private static final int PLAYCOUNT = 5;
    private static final String PUBLICATION_DATE = "17-04-2001";
    private static final String TRACK_DESCRIPTION = "description";
    private static final boolean OFFLINE_AVAILABLE = false;

    @BeforeEach
    public void setup() {
        sut = new TrackMapperImpl();

        mockedResultset = Mockito.mock(ResultSet.class);
    }

    @Nested
    @DisplayName("toTrackDTOList() unit tests")
    class ToTrackDTOListTest {
        @Test
        @DisplayName("Test toTrackDTOList() maps resultset")
        public void testToTrackDTOListMapsResultset() {
            try {
                // Arrange
                Mockito.when(mockedResultset.next()).thenReturn(true, false);
                Mockito.when(mockedResultset.getInt("trackId")).thenReturn(TRACK_ID);
                Mockito.when(mockedResultset.getString("title")).thenReturn(TITLE);
                Mockito.when(mockedResultset.getString("performerName")).thenReturn(PERFORMER_NAME);
                Mockito.when(mockedResultset.getInt("duration")).thenReturn(DURATION);
                Mockito.when(mockedResultset.getString("albumName")).thenReturn(ALBUM_NAME);
                Mockito.when(mockedResultset.getInt("playcount")).thenReturn(PLAYCOUNT);
                Mockito.when(mockedResultset.getString("publicationDate")).thenReturn(PUBLICATION_DATE);
                Mockito.when(mockedResultset.getString("trackDescription")).thenReturn(TRACK_DESCRIPTION);
                Mockito.when(mockedResultset.getBoolean("offlineAvailable")).thenReturn(OFFLINE_AVAILABLE);

                var expectedTrackId = TRACK_ID;
                var expectedTitle = TITLE;
                var expectedPerformerName = PERFORMER_NAME;
                var expectedDuration = DURATION;
                var expectedAlbumName = ALBUM_NAME;
                var expectedPlaycount = PLAYCOUNT;
                var expectedPublicationDate = PUBLICATION_DATE;
                var expectedTrackDescription = TRACK_DESCRIPTION;
                var expectedOfflineAvailable = OFFLINE_AVAILABLE;

                // Act
                var tracks = sut.toTrackDTOList(mockedResultset);

                var actualTrackId = tracks.get(0).getId();
                var actualTitle = tracks.get(0).getTitle();
                var actualPerformerName = tracks.get(0).getPerformer();
                var actualDuration = tracks.get(0).getDuration();
                var actualAlbumName = tracks.get(0).getAlbum();
                var actualPlaycount = tracks.get(0).getPlaycount();
                var actualPublicationDate = tracks.get(0).getPublicationDate();
                var actualTrackDescription = tracks.get(0).getDescription();
                var actualOfflineAvailable = tracks.get(0).isOfflineAvailable();

                // Assert
                assertEquals(expectedTrackId, actualTrackId);
                assertEquals(expectedTitle, actualTitle);
                assertEquals(expectedPerformerName, actualPerformerName);
                assertEquals(expectedDuration, actualDuration);
                assertEquals(expectedAlbumName, actualAlbumName);
                assertEquals(expectedPlaycount, actualPlaycount);
                assertEquals(expectedPublicationDate, actualPublicationDate);
                assertEquals(expectedTrackDescription, actualTrackDescription);
                assertEquals(expectedOfflineAvailable, actualOfflineAvailable);
            } catch (SQLException e) {
                fail();
            }
        }

        @Test
        @DisplayName("Test toTrackDTOList() returns the right amount of tracks")
        public void testToTrackDTOListReturnsTheRightAmountOfTracks() {
            try {
                // Arrange
                Mockito.when(mockedResultset.next()).thenReturn(true, true, false);

                var expectedNPlaylists = 2;

                // Act
                var tracks = sut.toTrackDTOList(mockedResultset);

                var actualNPlaylists = tracks.size();

                // Assert
                assertEquals(expectedNPlaylists, actualNPlaylists);
            } catch (SQLException e) {
                fail();
            }
        }
    }
}
