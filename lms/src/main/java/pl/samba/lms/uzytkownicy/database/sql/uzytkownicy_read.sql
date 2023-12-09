DELIMITER //

DROP PROCEDURE IF EXISTS lms.uzytkownicy_read;
CREATE PROCEDURE uzytkownicy_read(
	IN pk_id_uzytk INT
)
BEGIN
	IF pk_id_uzytk IS NOT NULL THEN
			SELECT
				u.id_uzytk,
				u.imie,
				u.nazwisko,
				u.tyt_nauk,
				u.login,
				u.haslo,
				u.email,
				u.telefon,
				u.data_urodz,
				u.`status`,
				r.nazwa AS rola,
				z.id_zdjecia,
				z.nazwa AS nazwa_pliku,
				z.zdjecie AS plik,
				z.ext,
				z.alt
			FROM lms.uzytkownicy u
			JOIN lms.role r ON u.id_roli = r.id_roli
			JOIN lms.zdjecia z ON u.id_zdjecia = z.id_zdjecia
			WHERE u.id_uzytk = pk_id_uzytk
			ORDER BY u.id_uzytk;
	ELSE
			SELECT
				u.id_uzytk,
				u.imie,
				u.nazwisko,
				u.tyt_nauk,
				u.login,
				u.haslo,
				u.email,
				u.telefon,
				u.data_urodz,
				u.`status`,
				r.nazwa AS rola,
				z.id_zdjecia,
				z.nazwa AS nazwa_pliku,
				z.zdjecie AS plik,
				z.ext,
				z.alt
			FROM lms.uzytkownicy u
			JOIN lms.role r ON u.id_roli = r.id_roli
			JOIN lms.zdjecia z ON u.id_zdjecia = z.id_zdjecia
			ORDER BY u.id_uzytk;
	END IF;
END //

DELIMITER ;