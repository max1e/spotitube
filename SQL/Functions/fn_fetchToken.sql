USE Spotitube;

DELIMITER $$
DROP FUNCTION IF EXISTS fn_fetchToken$$

CREATE FUNCTION fn_fetchToken (
	username varchar(255)
)
RETURNS varchar(14)
BEGIN
    RETURN "1234-1234-1234";
END$$

DELIMITER ;