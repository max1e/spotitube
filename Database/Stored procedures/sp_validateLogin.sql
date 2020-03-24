USE Spotitube;

DELIMITER $$
DROP PROCEDURE IF EXISTS sp_validateLogin$$

CREATE PROCEDURE sp_validateLogin(
	IN username varchar(255), 
    IN hashedPassword char(64)
)
BEGIN
	-- If username and password don't match throw error
	IF NOT EXISTS (
		SELECT *
		FROM Users u
		WHERE u.username = username
		AND u.hashedPassword = hashedPassword
	)
	THEN
		SET @exception = (SELECT exceptionName FROM HTTPExceptions WHERE statusCode = 1);
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = @exception;
	END IF;
    
    -- Give user new token
    UPDATE Users u
    SET u.token = fn_fetchToken(userId)
	WHERE u.username = username
    AND u.hashedPassword = hashedPassword;
    
    -- Return token and users full name
	SELECT token, CONCAT(firstName, " ", lastName) AS fullName
	FROM Users u
	WHERE u.username = username
    AND u.hashedPassword = hashedPassword;
END$$

DELIMITER ;