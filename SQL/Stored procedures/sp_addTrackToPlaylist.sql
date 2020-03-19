USE Spotitube;

DELIMITER $$
DROP PROCEDURE IF EXISTS sp_addTrackToPlaylist$$

CREATE PROCEDURE sp_getPlaylistsTracks(
	IN token char(14),
    IN playlistId int,
    IN trackId int
)
BEGIN
	INSERT INTO PlaylistsTracks (playlistId, trackId)
    VALUES (playlistId, trackId);
END$$

DELIMITER ;