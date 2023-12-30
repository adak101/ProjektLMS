DELIMITER //

DROP PROCEDURE IF EXISTS lms.przedmioty_ins;
CREATE PROCEDURE przedmioty_ins(
	OUT pk_id_przedm INT,
	IN p_nazwa VARCHAR(100),
	IN p_id_prow INT,
	IN p_limit_miejsc INT,
	IN p_opis VARCHAR(2000),
	IN p_war_zalicz VARCHAR(2000),
	IN p_id_okresu INT,
	IN p_kod_status VARCHAR(3),
	IN p_rejestr_uczn BOOLEAN
)
BEGIN
	DECLARE v_kod VARCHAR(10);
	DECLARE v_id_status INT;
	
	SET v_kod = generuj_kod_przedm_func(p_id_okresu,p_nazwa);

	SELECT ps.id_status
	INTO v_id_status
	FROM przedmiot_status ps
	WHERE ps.kod = p_kod_status;
	
	INSERT INTO przedmioty(kod, nazwa, id_prow, limit_miejsc, opis, war_zalicz, id_okresu, id_status, rejestr_uczn)
	VALUES (v_kod, p_nazwa, p_id_prow, p_limit_miejsc, p_opis, p_war_zalicz, p_id_okresu, v_id_status, p_rejestr_uczn);

	SELECT LAST_INSERT_ID() INTO pk_id_przedm;
END //

DELIMITER ;
