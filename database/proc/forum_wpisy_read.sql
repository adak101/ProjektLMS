DELIMITER //
DROP PROCEDURE IF EXISTS lms.forum_wpisy_read;
CREATE PROCEDURE forum_wpisy_read(
    IN p_pk_id_wpis INT
)
BEGIN
    SELECT *
    FROM forum_wpisy
    WHERE id_wpis = p_pk_id_wpis;
END //
DELIMITER ;