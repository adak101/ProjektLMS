DELIMITER //
DROP PROCEDURE IF EXISTS powiadomienia_upd;
CREATE PROCEDURE powiadomienia_upd(
    IN pk_id_powiadom INT,
    IN p_id_odbiorcy INT,
    IN p_tresc VARCHAR(700),
    IN p_data_wstaw DATETIME
)
BEGIN
    UPDATE powiadomienia
    SET
        id_odbiorcy = p_id_odbiorcy,
        tresc = p_tresc,
        data_wstaw = p_data_wstaw
    WHERE id_powiadom = pk_id_powiadom;
END //
DELIMITER ;