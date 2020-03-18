USE Spotitube;

DELIMITER $$
DROP PROCEDURE IF EXISTS sp_validateToken$$

CREATE PROCEDURE sp_validateToken(
	IN token varchar(14)
)
BEGIN
	SELECT COUNT(*) AS tokenAccepted
	FROM Users u
	WHERE u.token = token;
END$$

DELIMITER ;