DELIMITER //

DROP PROCEDURE IF EXISTS lms.zdjecia_ins;
CREATE PROCEDURE zdjecia_ins(
	OUT pk_id_zdjecia INT,
	IN p_zdjecie BLOB,
	IN p_tekst_alt VARCHAR(20)
)
BEGIN
	INSERT INTO zdjecia(zdjecie, tekst_alt)
	VALUES (p_zdjecie, p_tekst_alt);

	SELECT LAST_INSERT_ID() INTO pk_id_zdjecia;
END //

DELIMITER ;
