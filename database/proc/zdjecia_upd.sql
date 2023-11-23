DELIMITER //
DROP PROCEDURE IF EXISTS lms.zdjecia_upd;
CREATE PROCEDURE zdjecia_upd(
	IN pk_id_zdjecia INT,
	IN p_zdjecie BLOB,
	IN p_tekst_alt VARCHAR(20)
)
BEGIN
	UPDATE zdjecia
	SET zdjecie = p_zdjecie, tekst_alt = p_tekst_alt
	WHERE id_zdjecia = pk_id_zdjecia;
END //
DELIMITER ;