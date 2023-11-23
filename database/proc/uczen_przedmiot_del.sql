DELIMITER //
DROP PROCEDURE IF EXISTS lms.uczen_przedmiot_del;
CREATE PROCEDURE uczen_przedmiot_del(
	IN pk_id_oceny INT
)
BEGIN
	DELETE FROM uczen_przedmiot
	WHERE id_oceny = pk_id_oceny;
END //
DELIMITER ;