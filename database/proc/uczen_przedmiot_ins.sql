DELIMITER //

DROP PROCEDURE IF EXISTS lms.uczen_przedmiot_ins;
CREATE PROCEDURE uczen_przedmiot_ins(
	OUT pk_id_oceny INT,
	IN p_id_przedm INT,
	IN p_id_ucznia INT,
	IN p_ocena INT,
	IN p_data_wystaw_oc DATETIME
)
BEGIN
	INSERT INTO uczen_przedmiot(id_przedm, id_ucznia, ocena, data_wystaw_oc)
	VALUES (p_id_przedm, p_id_ucznia, p_ocena, p_data_wystaw_oc);

	SELECT LAST_INSERT_ID() INTO pk_id_oceny;
END //

DELIMITER ;
