DELIMITER //
DROP PROCEDURE IF EXISTS raport_upd;
CREATE PROCEDURE raport_upd(
    IN pk_id_raportu INT,
    IN p_id_uzytk INT,
    IN p_nazwa VARCHAR(50),
    IN p_data_wystaw DATETIME,
    IN p_raport BLOB
)
BEGIN
    UPDATE raport
    SET
        id_uzytk = p_id_uzytk,
        nazwa = p_nazwa,
        data_wystaw = p_data_wystaw,
        raport = p_raport
    WHERE id_raportu = pk_id_raportu;
END //
DELIMITER ;