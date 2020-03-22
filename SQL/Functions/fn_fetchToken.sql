USE Spotitube;

DELIMITER $$
DROP FUNCTION IF EXISTS fn_fetchToken$$

CREATE FUNCTION fn_fetchToken (
	userId int
) 
RETURNS varchar(14)
DETERMINISTIC
BEGIN
	SET @possibleChars = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789';
    SET @nChars = CHAR_LENGTH(@possibleChars);
	RETURN CONCAT(
		SUBSTRING(@possibleChars, rand(@seed:=round(rand(userId)*4294967296)) * @nChars+1, 1),
		SUBSTRING(@possibleChars, rand(@seed:=round(rand(@seed)*4294967296)) * @nChars+1, 1),
		SUBSTRING(@possibleChars, rand(@seed:=round(rand(@seed)*4294967296)) * @nChars+1, 1),
		SUBSTRING(@possibleChars, rand(@seed:=round(rand(@seed)*4294967296)) * @nChars+1, 1),
		'-',
		SUBSTRING(@possibleChars, rand() * @nChars+1, 1),
		SUBSTRING(@possibleChars, rand() * @nChars+1, 1),
		SUBSTRING(@possibleChars, rand() * @nChars+1, 1),
		SUBSTRING(@possibleChars, rand() * @nChars+1, 1),
		'-',
		SUBSTRING(@possibleChars, rand() * @nChars+1, 1),
		SUBSTRING(@possibleChars, rand() * @nChars+1, 1),
		SUBSTRING(@possibleChars, rand() * @nChars+1, 1),
		SUBSTRING(@possibleChars, rand() * @nChars+1, 1)
	);
END$$

DELIMITER ;