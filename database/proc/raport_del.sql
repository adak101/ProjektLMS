DELIMITER //
DROP PROCEDURE IF EXISTS raport_del;
CREATE PROCEDURE raport_del(
    IN pk_id_raportu INT
)
BEGIN
    DELETE FROM raport
    WHERE id_raportu = pk_id_raportu;
END //
DELIMITER ;