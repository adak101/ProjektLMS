package pl.samba.lms.przedmioty.okresy;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;


@Getter
@Setter
@RequiredArgsConstructor
public class Okres {
    private final Integer idOkresu;
    private String kod;
    private LocalDateTime dataPoczatku;
    private LocalDateTime dataKonca;

    public Okres(
            Integer idOkresu,
            String kod,
            LocalDateTime dataPoczatku,
            LocalDateTime dataKonca
    ) {
        this.idOkresu = idOkresu;
        this.kod = kod;
        this.dataPoczatku = dataPoczatku;
        this.dataKonca = dataKonca;
    }
}
