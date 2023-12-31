DELIMITER //
DROP PROCEDURE IF EXISTS lms.powiadomienia_ins;
CREATE PROCEDURE powiadomienia_ins(
    OUT pk_id_powiadom INT,
    IN p_id_odbiorcy INT,
    IN p_tresc VARCHAR(1000)
)
BEGIN
    INSERT INTO powiadomienia(id_odbiorcy, tresc, data_wstaw, id_flagi)
    VALUES (p_id_odbiorcy, p_tresc, NOW(), DEFAULT);

    SET pk_id_powiadom = LAST_INSERT_ID();
END //
DELIMITER ;