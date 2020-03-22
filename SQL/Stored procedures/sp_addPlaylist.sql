USE Spotitube;

DELIMITER $$
DROP PROCEDURE IF EXISTS sp_addPlaylist$$

CREATE PROCEDURE sp_addPlaylist(
	IN token char(14),
    IN playlistName VARCHAR(255)
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
    
	INSERT INTO Playlists (playlistName, playlistOwner)
    VALUES (playlistName, (SELECT userId FROM Users WHERE Users.token = token));
END$$

DELIMITER ;