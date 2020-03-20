USE Spotitube;

DELIMITER $$
DROP PROCEDURE IF EXISTS sp_getAllPlaylists$$

CREATE PROCEDURE sp_getAllPlaylists(
	IN token char(14)
)
BEGIN
	-- If token doesn't match throw error
	IF (SELECT COUNT(*) AS tokenAccepted
		FROM Users u
		WHERE u.token = @token)
        != 1
	THEN
		SET @exception = (SELECT exceptionName FROM HTTPExceptions WHERE statusCode = 401);
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = @exception;
	END IF;
    
	SET @playlistOwner = (SELECT userId FROM Users WHERE Users.token = token);
    
	SELECT Playlists.playlistId, playlistName, playlistOwner = @playlistOwner AS isOwner, SUM(Tracks.duration) AS duration
    FROM Playlists
		INNER JOIN PlaylistsTracks
			ON Playlists.playlistId = PlaylistsTracks.playlistId
		INNER JOIN Tracks
			ON PlaylistsTracks.trackId = Tracks.trackId
    WHERE Users.token = token
	GROUP BY Playlists.playlistId;
END$$

DELIMITER ;