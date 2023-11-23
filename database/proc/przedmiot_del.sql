DELIMITER //
DROP PROCEDURE IF EXISTS lms.przedmiot_del;
CREATE PROCEDURE przedmiot_del(
	IN pk_id_przedm INT
)
BEGIN
	DELETE FROM przedmiot
	WHERE id_przedm = pk_id_przedm;
END //

DELIMITER ;