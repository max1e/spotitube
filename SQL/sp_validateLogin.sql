USE Spotitube;

DELIMITER $$
DROP PROCEDURE IF EXISTS sp_validateLogin$$

CREATE PROCEDURE sp_validateLogin(
	IN username varchar(255), 
    IN hashedPassword char(64)
)
BEGIN
	SELECT COUNT(*) AS loginAccepted
	FROM Users u
	WHERE (u.username = username
	AND u.hashedPassword = hashedPassword);
END$$

DELIMITER ;