DELIMITER //
DROP PROCEDURE IF EXISTS uzytkownicy_del;
CREATE PROCEDURE uzytkownicy_del(
	IN pk_id_uzytk INT
)
BEGIN
	DELETE FROM uzytkownicy
	WHERE id_uzytk = pk_id_uzytk;
END //
DELIMITER ;