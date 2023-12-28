package pl.samba.lms.przedmioty.zadania.zadania.api;

import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import pl.samba.lms.przedmioty.przedmioty.api.PrzedmiotyController;
import pl.samba.lms.przedmioty.zadania.zadania.Zadanie;

import java.time.LocalDateTime;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Getter
public class ZadanieModel extends RepresentationModel<ZadanieModel> {
    private final Integer id;
    private final LocalDateTime dataWstawienia;
    private final LocalDateTime dataPoczatku;
    private final LocalDateTime dataKonca;
    private final String tresc;

    public ZadanieModel(Zadanie z){
        this.id = z.getIdZadania();
        this.dataWstawienia = z.getDataWstawienia();
        this.dataPoczatku = z.getDataPoczatku();
        this.dataKonca = z.getDataKonca();
        this.tresc = z.getTresc().toString();

        add(WebMvcLinkBuilder.linkTo(
                methodOn(PrzedmiotyController.class)
                        .get(z.getIdPrzedmiotu())).withRel("przedmiot"));
    }
}
