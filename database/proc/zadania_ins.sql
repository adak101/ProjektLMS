DELIMITER //

DROP PROCEDURE IF EXISTS lms.zadania_ins;
CREATE PROCEDURE zadania_ins(
	OUT pk_id_zadania INT,
	IN p_id_przedm INT,
	IN p_data_pocz DATETIME,
	IN p_data_konc DATETIME,
	IN p_tresc BLOB
)
BEGIN
	INSERT INTO zadania(id_przedm, data_pocz, data_konc, tresc)
	VALUES (p_id_przedm, p_data_pocz, p_data_konc, p_tresc);

	SELECT LAST_INSERT_ID() INTO pk_id_zadania;
END //

DELIMITER ;
