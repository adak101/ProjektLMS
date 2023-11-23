DELIMITER //

DROP PROCEDURE IF EXISTS lms.przedmiot_status_ins;
CREATE PROCEDURE przedmiot_status_ins(
	OUT pk_id_status INT,
	IN p_kod VARCHAR(3),
	IN p_nazwa VARCHAR(20)
)
BEGIN
	INSERT INTO przedmiot_status(kod, nazwa)
	VALUES (p_kod, p_nazwa);

	SELECT LAST_INSERT_ID() INTO pk_id_status;
END //

DELIMITER ;
