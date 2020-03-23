USE Spotitube;

DELIMITER $$
DROP PROCEDURE IF EXISTS sp_getTracksNotInPlaylist$$

CREATE PROCEDURE sp_getTracksNotInPlaylist(
	IN token char(14),
	IN playlist int
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
    
    SELECT trackId, title, Performers.performerName, duration, Albums.albumName, playcount, publicationDate, trackDescription, offlineAvailable 
    FROM Tracks t1
		INNER JOIN Performers
			ON t1.performer = Performers.performerId
		INNER JOIN Albums
			ON t1.album = Albums.albumId
	WHERE NOT EXISTS (
		SELECT *
        FROM PlaylistsTracks
        WHERE playlistId = playlist
			AND trackId = t1.trackId
    );
END$$

DELIMITER ;