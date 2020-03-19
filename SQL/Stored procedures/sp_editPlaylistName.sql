USE Spotitube;

DELIMITER $$
DROP PROCEDURE IF EXISTS sp_editPlaylistName$$

CREATE PROCEDURE sp_editPlaylistName(
	IN token char(14),
    IN playlistId int,
    IN newPlaylistName VARCHAR(255)
)
BEGIN
	UPDATE Playlists
    SET playlistName = newPlaylistName
    WHERE Playlists.playlistId = playlistId;
END$$

DELIMITER ;