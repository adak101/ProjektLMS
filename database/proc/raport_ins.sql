DELIMITER //
DROP PROCEDURE IF EXISTS lms.raport_ins;
CREATE PROCEDURE raport_ins(
    OUT pk_id_raportu INT,
    IN p_id_uzytk INT,
    IN p_nazwa VARCHAR(50),
    IN p_data_wystaw DATETIME,
    IN p_raport BLOB
)
BEGIN
    INSERT INTO raport(id_uzytk, nazwa, data_wystaw, raport)
    VALUES (p_id_uzytk, p_nazwa, p_data_wystaw, p_raport);

    SET pk_id_raportu = LAST_INSERT_ID();
END //
DELIMITER ;