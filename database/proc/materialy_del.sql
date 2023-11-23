DELIMITER //
DROP PROCEDURE IF EXISTS lms.materialy_del;
CREATE PROCEDURE materialy_del(
	IN pk_id_mater INT
)
BEGIN
	DELETE FROM materialy
	WHERE id_mater = pk_id_mater;
END //

DELIMITER ;