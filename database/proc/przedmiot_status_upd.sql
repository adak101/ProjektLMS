DELIMITER //
DROP PROCEDURE IF EXISTS lms.przedmiot_status_del;
CREATE PROCEDURE przedmiot_status_del(
	IN pk_id_status INT
)
BEGIN
	DELETE FROM przedmiot_status
	WHERE id_status = pk_id_status;
END //

DROP PROCEDURE IF EXISTS lms.przedmiot_status_upd;
CREATE PROCEDURE przedmiot_status_upd(
	IN pk_id_status INT,
	IN p_kod VARCHAR(3),
	IN p_nazwa VARCHAR(20)
)
BEGIN
	UPDATE przedmiot_status
	SET kod = p_kod, nazwa = p_nazwa
	WHERE id_status = pk_id_status;
END //
DELIMITER ;