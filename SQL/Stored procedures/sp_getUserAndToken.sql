USE Spotitube;

DELIMITER $$
DROP PROCEDURE IF EXISTS sp_getUserAndToken$$

CREATE PROCEDURE sp_getUserAndToken(
	IN username varchar(255),
	IN hashedPassword varchar(64)
)
BEGIN
    UPDATE Users u
    SET u.token = fn_fetchToken(username)
	WHERE u.username = username
    AND u.hashedPassword = hashedPassword;
    
	SELECT token, CONCAT(firstName, lastName) AS fullName
	FROM Users u
	WHERE u.username = username
    AND u.hashedPassword = hashedPassword;
END$$

DELIMITER ;