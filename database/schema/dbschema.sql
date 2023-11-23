DROP DATABASE IF EXISTS lms;

CREATE DATABASE lms;
USE lms;

CREATE TABLE Zdjecia (
    id_zdjecia INT PRIMARY KEY AUTO_INCREMENT,
    zdjecie BLOB,
    tekst_alt VARCHAR(20),
    data_wstaw DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE role (
    id_roli INT PRIMARY KEY AUTO_INCREMENT,
    nazwa VARCHAR(10) UNIQUE
);

INSERT INTO ROLE(nazwa) VALUES ('ADMIN'), ('UCZEN'), ('NAUCZYCIEL');

CREATE TABLE Uzytkownicy (
    id_uzytk INT PRIMARY KEY AUTO_INCREMENT,
    imie VARCHAR(40),
    nazwisko VARCHAR(40),
    tyt_nauk VARCHAR(30) NULL,
    login VARCHAR(80) UNIQUE,
    haslo VARCHAR(40),
    email VARCHAR(40),
    telefon INT(9),
    data_urodz DATE,
    status BOOLEAN DEFAULT true,
    id_zdjecia INT,
    id_roli INT,
    FOREIGN KEY (id_zdjecia) REFERENCES Zdjecia(id_zdjecia),
    FOREIGN KEY (id_roli) REFERENCES role(id_roli)
);

CREATE TABLE Okresy (
    id_okresu INT PRIMARY KEY AUTO_INCREMENT,
    kod VARCHAR(10) UNIQUE,
    data_pocz DATETIME,
    data_konc DATETIME
);

CREATE TABLE Przedmiot_status (
    id_status INT PRIMARY KEY AUTO_INCREMENT,
    kod VARCHAR(3),
    nazwa VARCHAR(20)
);

INSERT INTO przedmiot_status(kod, nazwa) VALUES
	('2AP', 'DO ZATWIERDZENIA'),
	('APR', 'ZATWIERDZONY'),
	('REJ', 'ODRZUCONY'),
	('ACT', 'TRWAJĄCY'),
	('END', 'ZAKOŃCZONY');

CREATE TABLE Przedmiot (
    id_przedm INT PRIMARY KEY AUTO_INCREMENT,
    kod VARCHAR(10),
    nazwa VARCHAR(40),
    id_prow INT,
    limit_miejsc INT,
    opis VARCHAR(2000),
    war_zalicz VARCHAR(2000),
    id_okresu INT,
    id_status INT,
    rejestr_uczn BOOLEAN DEFAULT TRUE,
    
    FOREIGN KEY (id_prow) REFERENCES Uzytkownicy(id_uzytk),
    FOREIGN KEY (id_okresu) REFERENCES Okresy(id_okresu),
    FOREIGN KEY (id_status) REFERENCES Przedmiot_status(id_status)
);

CREATE TABLE Uczen_przedmiot (
    id_oceny INT PRIMARY KEY AUTO_INCREMENT,
    id_przedm INT,
    id_ucznia INT,
    ocena INT NULL,information_schema
    data_wystaw_oc DATETIME NULL,
    FOREIGN KEY (id_przedm) REFERENCES Przedmiot(id_przedm),
    FOREIGN KEY (id_ucznia) REFERENCES Uzytkownicy(id_uzytk)
);

CREATE TABLE Zadania (
    id_zadania INT PRIMARY KEY AUTO_INCREMENT,
    id_przedm INT,
    data_wstaw DATETIME DEFAULT CURRENT_TIMESTAMP,
    data_pocz DATETIME DEFAULT CURRENT_TIMESTAMP,
    data_konc DATETIME,
    tresc BLOB,
    FOREIGN KEY (id_przedm) REFERENCES Przedmiot(id_przedm)
);

CREATE TABLE Odpowiedzi_zadania (
    id_odpowiedzi INT PRIMARY KEY AUTO_INCREMENT,
    id_zadania INT,
    id_ucznia INT,
    tresc BLOB,
    koment VARCHAR(300) NULL,
    ocena INT NULL,
    data_wstaw DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_zadania) REFERENCES Zadania(id_zadania),
    FOREIGN KEY (id_ucznia) REFERENCES Uzytkownicy(id_uzytk)
);


CREATE TABLE Materialy (
    id_mater INT PRIMARY KEY AUTO_INCREMENT,
    id_przedm INT,
    data_wstaw DATETIME DEFAULT CURRENT_TIMESTAMP,
    lp INT,
    temat VARCHAR(100),
    material BLOB,
    opis VARCHAR(300) NULL,
    widocznosc BOOLEAN DEFAULT true,
    FOREIGN KEY (id_przedm) REFERENCES Przedmiot(id_przedm)
);

CREATE TABLE Forum_wpisy (
    id_wpis INT PRIMARY KEY AUTO_INCREMENT,
    id_przedm INT,
    id_uzytk INT,
    temat VARCHAR(200),
    tresc VARCHAR(700),
    data_wpis DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_przedm) REFERENCES Przedmiot(id_przedm),
    FOREIGN KEY (id_uzytk) REFERENCES Uzytkownicy(id_uzytk)
);

CREATE TABLE Forum_odp (
    id_odp INT PRIMARY KEY AUTO_INCREMENT,
    id_wpis INT,
    id_uzytk INT,
    tresc VARCHAR(700),
    data_wpis DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_wpis) REFERENCES Forum_wpisy(id_wpis),
    FOREIGN KEY (id_uzytk) REFERENCES Uzytkownicy(id_uzytk)
);

CREATE TABLE Powiadomienia (
    id_powiadom INT PRIMARY KEY AUTO_INCREMENT,
    id_odbiorcy INT,
    data_wstaw DATETIME DEFAULT CURRENT_TIMESTAMP,
    tresc VARCHAR(700),
    FOREIGN KEY (id_odbiorcy) REFERENCES Uzytkownicy(id_uzytk)
);

CREATE TABLE Raport (
    id_raportu INT PRIMARY KEY AUTO_INCREMENT,
    id_uzytk INT,
    nazwa VARCHAR(50),
    data_wystaw DATETIME DEFAULT CURRENT_TIMESTAMP,
    raport BLOB,
    FOREIGN KEY (id_uzytk) REFERENCES Uzytkownicy(id_uzytk)
);

