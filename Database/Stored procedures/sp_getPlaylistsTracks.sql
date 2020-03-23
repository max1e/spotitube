USE Spotitube;

DELIMITER $$
DROP PROCEDURE IF EXISTS sp_getPlaylistsTracks$$

CREATE PROCEDURE sp_getPlaylistsTracks(
	IN token char(14),
    IN playlistId int
)
BEGIN
	-- If token doesn't match throw error
	IF NOT EXISTS (
		SELECT *
		FROM Users u
		WHERE u.token = token
	)
	THEN
		SET @exception = (SELECT exceptionName FROM HTTPExceptions WHERE statusCode = 401);
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = @exception;
	END IF;

	SELECT Tracks.trackId, title, performerName, duration, albumName, playcount, publicationDate, trackDescription, offlineAvailable
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