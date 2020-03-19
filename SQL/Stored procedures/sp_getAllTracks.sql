USE Spotitube;

DELIMITER $$
DROP PROCEDURE IF EXISTS sp_getAllTracks$$

CREATE PROCEDURE sp_getAllTracks()
BEGIN
    SELECT trackId, title, Performers.performerName, duration, Albums.albumName, playcount, publicationDate, trackDescription, offlineAvailable 
    FROM Tracks
		INNER JOIN Performers
			ON Tracks.performer = Performers.performerId
		INNER JOIN Albums
			ON Tracks.album = Albums.albumId;
END$$

DELIMITER ;