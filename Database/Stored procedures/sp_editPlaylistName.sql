USE Spotitube;

DELIMITER $$
DROP PROCEDURE IF EXISTS sp_editPlaylistName$$

CREATE PROCEDURE sp_editPlaylistName(
	IN token char(14),
    IN playlistId int,
    IN newPlaylistName VARCHAR(255)
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
    
	UPDATE Playlists
    SET playlistName = newPlaylistName
    WHERE Playlists.playlistId = playlistId;
END$$

DELIMITER ;