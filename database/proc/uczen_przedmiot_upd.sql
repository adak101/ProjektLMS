DELIMITER //
DROP PROCEDURE IF EXISTS lms.uczen_przedmiot_upd;
CREATE PROCEDURE uczen_przedmiot_upd(
	IN pk_id_oceny INT,
	IN p_id_przedm INT,
	IN p_id_ucznia INT,
	IN p_ocena INT,
	IN p_data_wystaw_oc DATETIME
)
BEGIN
	UPDATE uczen_przedmiot
	SET id_przedm = p_id_przedm, id_ucznia = p_id_ucznia, ocena = p_ocena, data_wystaw_oc = p_data_wystaw_oc
	WHERE id_oceny = pk_id_oceny;
END //
DELIMITER ;