USE Spotitube;

DELIMITER $$
DROP PROCEDURE IF EXISTS sp_getPlaylistsTracks$$

CREATE PROCEDURE sp_getPlaylistsTracks(
	IN token char(14),
    IN playlistId int
)
BEGIN
	SELECT Tracks.trackId, title, performer, duration, albumName, playcount, publicationDate, trackDescription, offlineAvailable
    FROM PlaylistsTracks
		INNER JOIN Tracks
			ON PlaylistsTracks.trackId = Tracks.trackId
		INNER JOIN Albums
			ON Tracks.album = Albums.albumId
		INNER JOIN Performers
			ON Tracks.performer = Performers.performerId
	WHERE PlaylistsTracks.playlistId = playlistId;
END$$

DELIMITER ;