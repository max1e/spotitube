USE Spotitube;

DELIMITER $$
DROP PROCEDURE IF EXISTS sp_removeTrackFromPlaylist$$

CREATE PROCEDURE sp_removeTrackFromPlaylist(
	IN token char(14),
    IN playlistId int,
    IN trackId int
)
BEGIN
	DELETE FROM PlaylistsTracks
	WHERE PlaylistsTracks.playlistId = playlistId
    AND PlaylistsTracks.trackId = trackId;
END$$

DELIMITER ;