DELIMITER //

DROP PROCEDURE IF EXISTS lms.przedmiot_ins;
CREATE PROCEDURE przedmiot_ins(
	OUT pk_id_przedm INT,
	IN p_kod VARCHAR(10),
	IN p_nazwa VARCHAR(40),
	IN p_id_prow INT,
	IN p_limit_miejsc INT,
	IN p_opis VARCHAR(2000),
	IN p_war_zalicz VARCHAR(2000),
	IN p_id_okresu INT,
	IN p_id_status INT,
	IN p_rejestr_uczn BOOLEAN
)
BEGIN
	INSERT INTO przedmiot(kod, nazwa, id_prow, limit_miejsc, opis, war_zalicz, id_okresu, id_status, rejestr_uczn)
	VALUES (p_kod, p_nazwa, p_id_prow, p_limit_miejsc, p_opis, p_war_zalicz, p_id_okresu, p_id_status, p_rejestr_uczn);

	SELECT LAST_INSERT_ID() INTO pk_id_przedm;
END //

DELIMITER ;
