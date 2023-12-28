DELIMITER //
DROP PROCEDURE IF EXISTS lms.zadania_upd;
CREATE PROCEDURE zadania_upd(
	IN pk_id_zadania INT,
	IN p_id_przedm INT,
	IN p_data_pocz DATETIME,
	IN p_data_konc DATETIME,
	IN p_tresc BLOB
)
BEGIN
	UPDATE zadania
	SET id_przedm = p_id_przedm, data_pocz = p_data_pocz, data_konc = p_data_konc, tresc = p_tresc
	WHERE id_zadania = pk_id_zadania;
END //
DELIMITER ;