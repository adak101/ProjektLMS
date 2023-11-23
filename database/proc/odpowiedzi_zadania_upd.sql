DELIMITER //
DROP PROCEDURE IF EXISTS lms.odpowiedzi_zadania_upd;
CREATE PROCEDURE odpowiedzi_zadania_upd(
	IN pk_id_odpowiedzi INT,
	IN p_id_zadania INT,
	IN p_id_ucznia INT,
	IN p_tresc BLOB,
	IN p_koment VARCHAR(300),
	IN p_ocena INT
)
BEGIN
	UPDATE odpowiedzi_zadania
	SET id_zadania = p_id_zadania, id_ucznia = p_id_ucznia, tresc = p_tresc, koment = p_koment, ocena = p_ocena
	WHERE id_odpowiedzi = pk_id_odpowiedzi;
END //
DELIMITER ;