USE Spotitube;

DELIMITER $$
DROP PROCEDURE IF EXISTS sp_getAllPlaylists$$

CREATE PROCEDURE sp_getAllPlaylists(
	IN token char(14)
)
BEGIN
	-- If token doesn't match throw error
	IF NOT EXISTS (
		SELECT *
		FROM Users u
		WHERE token = token
	)
	THEN
		SET @exception = (SELECT exceptionName FROM HTTPExceptions WHERE statusCode = 401);
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = @exception;
	END IF;
    
	SET @playlistOwner = (SELECT userId FROM Users WHERE Users.token = token);
    
	SELECT p.playlistId, playlistName, playlistOwner = @playlistOwner AS isOwner, SUM(Tracks.duration) AS duration
    FROM Playlists p
		INNER JOIN PlaylistsTracks
			ON p.playlistId = PlaylistsTracks.playlistId
		INNER JOIN Tracks
			ON PlaylistsTracks.trackId = Tracks.trackId
	GROUP BY p.playlistId;
END$$

DELIMITER ;