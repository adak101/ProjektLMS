DELIMITER //

DROP PROCEDURE IF EXISTS lms.zadania_read;
CREATE PROCEDURE zadania_read(
	IN pk_id_zadania INT,
	IN p_size INT,
	IN p_page INT,
	IN p_kod VARCHAR(100)
)
BEGIN
	DECLARE v_offset  INT; 
	IF pk_id_zadania IS NOT NULL THEN
		SELECT
			z.id_zadania,
			z.id_przedm,
			z.data_wstaw,
			z.data_pocz,
			z.data_konc,
			z.tresc,
			z.id_typu
		FROM lms.zadania z
		WHERE z.id_zadania = pk_id_zadania;
	ELSEIF p_size IS NOT NULL OR p_page IS NOT NULL THEN
		SET v_offset = p_page * p_size;

		SELECT
			z.id_zadania,
			z.id_przedm,
			z.data_wstaw,
			z.data_pocz,
			z.data_konc,
			z.tresc,
			z.id_typu
		FROM lms.zadania z
		WHERE EXISTS(
			SELECT p.id_przedm
			FROM  lms.przedmioty p
			WHERE  z.id_przedm = p.id_przedm
				AND p.kod = p_kod
		)
		ORDER BY z.id_zadania
		LIMIT p_size
		OFFSET v_offset;
	ELSE
		SELECT
			z.id_zadania,
			z.id_przedm,
			z.data_wstaw,
			z.data_pocz,
			z.data_konc,
			z.tresc,
			z.id_typu
		FROM lms.zadania z
		WHERE EXISTS(
			SELECT p.id_przedm
			FROM  lms.przedmioty p
			WHERE  z.id_przedm = p.id_przedm
				AND p.kod = p_kod
		)
		ORDER BY z.id_zadania;
	END IF;
END //

DELIMITER ;
