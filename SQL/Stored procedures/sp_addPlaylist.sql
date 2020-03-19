USE Spotitube;

DELIMITER $$
DROP PROCEDURE IF EXISTS sp_addPlaylist$$

CREATE PROCEDURE sp_addPlaylist(
	IN token char(14),
    IN playlistName VARCHAR(255)
)
BEGIN
	INSERT INTO Playlists
    VALUES (playlistName, (SELECT username FROM Users WHERE Users.token = token));
    
    SELECT last_insert_id() as playlistId;
END$$

DELIMITER ;