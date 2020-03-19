USE Spotitube;

DELIMITER $$
DROP PROCEDURE IF EXISTS sp_deletePlaylist$$

CREATE PROCEDURE sp_deletePlaylist(
	IN token char(14),
    IN playlistId int
)
BEGIN
	DELETE FROM Playlists
	WHERE Playlists.playlistId = playlistId;
END$$

DELIMITER ;