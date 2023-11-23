DELIMITER //
DROP PROCEDURE IF EXISTS lms.materialy_upd;
CREATE PROCEDURE materialy_upd(
	IN pk_id_mater INT,
	IN p_id_przedm INT,
	IN p_lp INT,
	IN p_temat VARCHAR(100),
	IN p_material BLOB,
	IN p_opis VARCHAR(300),
	IN p_widocznosc BOOLEAN
)
BEGIN
	UPDATE materialy
	SET id_przedm = p_id_przedm, lp = p_lp, temat = p_temat, material = p_material, opis = p_opis, widocznosc = p_widocznosc
	WHERE id_mater = pk_id_mater;
END //
DELIMITER ;