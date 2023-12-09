DELIMITER //
DROP PROCEDURE IF EXISTS lms.zdjecia_upd;
CREATE PROCEDURE zdjecia_upd(
	IN pk_id_zdjecia INT,
	IN p_zdjecie BLOB,
	IN p_alt VARCHAR(20),
	IN p_ext VARCHAR(5),
	IN p_nazwa VARCHAR(40)
)
BEGIN
	UPDATE zdjecia
	SET 
		zdjecie = p_zdjecie, 
		alt = p_alt,
		ext = p_ext,
		nazwa = p_nazwa
	WHERE id_zdjecia = pk_id_zdjecia;
END //
DELIMITER ;