DELIMITER //
DROP PROCEDURE IF EXISTS lms.przedmiot_upd;
CREATE PROCEDURE przedmiot_upd(
	IN pk_id_przedm INT,
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
	UPDATE przedmiot
	SET kod = p_kod, nazwa = p_nazwa, id_prow = p_id_prow, limit_miejsc = p_limit_miejsc, opis = p_opis, war_zalicz = p_war_zalicz,
		id_okresu = p_id_okresu, id_status = p_id_status, rejestr_uczn = p_rejestr_uczn
	WHERE id_przedm = pk_id_przedm;
END //
DELIMITER ;