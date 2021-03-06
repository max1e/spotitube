USE Spotitube;

DELIMITER $$
DROP PROCEDURE IF EXISTS sp_addTrackToPlaylist$$

CREATE PROCEDURE sp_addTrackToPlaylist(
	IN token char(14),
    IN playlistId int,
    IN trackId int
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
    
	INSERT INTO PlaylistsTracks (playlistId, trackId)
    VALUES (playlistId, trackId);
END$$

DELIMITER ;