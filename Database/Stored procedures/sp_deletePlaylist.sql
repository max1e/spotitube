USE Spotitube;

DELIMITER $$
DROP PROCEDURE IF EXISTS sp_deletePlaylist$$

CREATE PROCEDURE sp_deletePlaylist(
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
    
    -- If user associated to token isn't the owner of this playlist, throw error
	IF NOT EXISTS (
		SELECT *
		FROM Users u
			INNER JOIN Playlists p
				ON u.userId = p.playlistOwner
		WHERE u.token = token
        AND p.playlistId = playlistId
	)
	THEN
		SET @exception = (SELECT exceptionName FROM HTTPExceptions WHERE statusCode = 402);
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = @exception;
	END IF;
    
	DELETE FROM Playlists
	WHERE Playlists.playlistId = playlistId;
END$$

DELIMITER ;