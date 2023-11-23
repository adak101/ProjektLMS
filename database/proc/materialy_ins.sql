DELIMITER //

DROP PROCEDURE IF EXISTS lms.materialy_ins;
CREATE PROCEDURE materialy_ins(
	OUT pk_id_mater INT,
	IN p_id_przedm INT,
	IN p_lp INT,
	IN p_temat VARCHAR(100),
	IN p_material BLOB,
	IN p_opis VARCHAR(300),
	IN p_widocznosc BOOLEAN
)
BEGIN
	INSERT INTO materialy(id_przedm, lp, temat, material, opis, widocznosc)
	VALUES (p_id_przedm, p_lp, p_temat, p_material, p_opis, p_widocznosc);

	SELECT LAST_INSERT_ID() INTO pk_id_mater;
END //

DELIMITER ;
