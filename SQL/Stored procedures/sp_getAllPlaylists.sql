USE Spotitube;

DELIMITER $$
DROP PROCEDURE IF EXISTS sp_getAllPlaylists$$

CREATE PROCEDURE sp_getAllPlaylists(
	IN token char(14)
)
BEGIN
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