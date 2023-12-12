package pl.samba.lms.uzytkownicy;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import pl.samba.lms.utils.constants.Status;
import pl.samba.lms.utils.constants.Role;
import pl.samba.lms.zdjecie.Zdjecie;

import java.util.Date;

@Getter
@Setter
@RequiredArgsConstructor
public class Uzytkownik {
    private final Integer idUzytk;
    private String imie;
    private String nazwisko;
    private String tytNauk;
    private final String login;
    private String haslo;
    private String email;
    private Integer telefon;
    private Date dataUrodz;
    private Status status;
    private Zdjecie zdjecie;
    private Role rola;

    public Uzytkownik(
            Integer idUzytk,
            String imie,
            String nazwisko,
            String tytNauk,
            String login,String haslo,
            String email,
            Integer telefon,
            Date dataUrodz,
            Status status,
            Zdjecie zdjecie,
            Role rola) {
        this.idUzytk = idUzytk;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.tytNauk = tytNauk;
        this.login = login;
        this.haslo = haslo;
        this.email = email;
        this.telefon = telefon;
        this.dataUrodz = dataUrodz;
        this.status = status;
        this.zdjecie = zdjecie;
        this.rola = rola;
    }

    // Konstruktor argumentowy z adnotacją @JsonCreator
    @JsonCreator
    public Uzytkownik(
                      @JsonProperty("imie") String imie,
                      @JsonProperty("nazwisko") String nazwisko,
                      @JsonProperty("tytNauk") String tytNauk,
                      @JsonProperty("haslo") String haslo,
                      @JsonProperty("email") String email,
                      @JsonProperty("telefon") Integer telefon,
                      @JsonProperty("dataUrodz") Date dataUrodz,
                      @JsonProperty("status") Status status,
                      @JsonProperty("zdjecie") Zdjecie zdjecie,
                      @JsonProperty("rola") Role rola) {
        this.idUzytk = null;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.tytNauk = tytNauk;
        this.login = null;
        this.haslo = haslo;
        this.email = email;
        this.telefon = telefon;
        this.dataUrodz = dataUrodz;
        this.status = status;
        this.zdjecie = zdjecie;
        this.rola = rola;
    }
}
