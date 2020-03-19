USE Spotitube;

DELIMITER $$
DROP PROCEDURE IF EXISTS sp_getTracksNotInPlaylist$$

CREATE PROCEDURE sp_getTracksNotInPlaylist(
	IN playlist int
)
BEGIN
    SELECT trackId, title, Performers.performerName, duration, Albums.albumName, playcount, publicationDate, trackDescription, offlineAvailable 
    FROM Tracks t1
		INNER JOIN Performers
			ON Tracks.performer = Performers.performerId
		INNER JOIN Albums
			ON Tracks.album = Albums.albumId
	WHERE NOT EXISTS (
		SELECT *
        FROM PlaylistsTracks
        WHERE playlistId = playlist
			AND trackId = t1.trackId
    );
END$$

DELIMITER ;