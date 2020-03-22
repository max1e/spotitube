USE Spotitube;

DELIMITER $$
DROP PROCEDURE IF EXISTS sp_getAllTracks$$

CREATE PROCEDURE sp_getAllTracks(
	IN token char(14)
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
    FROM Tracks t
		INNER JOIN Performers
			ON t.performer = Performers.performerId
		INNER JOIN Albums
			ON t.album = Albums.albumId;
END$$

DELIMITER ;