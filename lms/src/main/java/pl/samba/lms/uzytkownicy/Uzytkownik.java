package pl.samba.lms.uzytkownicy;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pl.samba.lms.utils.constants.Status;
import pl.samba.lms.utils.constants.Role;
import pl.samba.lms.zdjecie.Zdjecie;

import java.util.Date;

@Getter
@RequiredArgsConstructor
public class Uzytkownik {
    private final Integer idUzytk;
    private final String imie;
    private final String nazwisko;
    private final String tytNauk;
    private final String login;
    private final String haslo;
    private final String email;
    private final int telefon;
    private final Date dataUrodz;
    private final Status status;
    private final Zdjecie zdjecie;
    private final Role rola;
}
