DELIMITER //

DROP PROCEDURE IF EXISTS lms.zdjecia_ins;
CREATE PROCEDURE zdjecia_ins(
	OUT pk_id_zdjecia INT,
	IN p_zdjecie BLOB,
	IN p_alt VARCHAR(20),
	IN p_ext VARCHAR(5),
	IN p_nazwa VARCHAR(40)
)
BEGIN
	INSERT INTO zdjecia(zdjecie, alt, ext, nazwa)
	VALUES (p_zdjecie, p_alt, p_ext, p_nazwa);

	SELECT LAST_INSERT_ID() INTO pk_id_zdjecia;
END //

DELIMITER ;
