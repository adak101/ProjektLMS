DELIMITER //
DROP PROCEDURE IF EXISTS lms.uczen_przedmiot_upd;
CREATE PROCEDURE uczen_przedmiot_upd(
	INOUT pk_id_oceny INT,
	IN p_ocena INT,
	IN p_data_wystaw_oc DATETIME
)
BEGIN
	UPDATE uczen_przedmiot up
	SET ocena = p_ocena, data_wystaw_oc = SYSDATE()
	WHERE up.id_oceny =pk_id_oceny;
	
END //
DELIMITER ;