DELIMITER //

DROP PROCEDURE IF EXISTS lms.odpowiedzi_zadania_ins;
CREATE PROCEDURE odpowiedzi_zadania_ins(
	OUT pk_id_odpowiedzi INT,
	IN p_id_zadania INT,
	IN p_id_ucznia INT,
	IN p_tresc BLOB,
	IN p_koment VARCHAR(300),
	IN p_ocena INT
)
BEGIN
	INSERT INTO odpowiedzi_zadania(id_zadania, id_ucznia, tresc, koment, ocena)
	VALUES (p_id_zadania, p_id_ucznia, p_tresc, p_koment, p_ocena);

	SELECT LAST_INSERT_ID() INTO pk_id_odpowiedzi;
END //

DELIMITER ;
