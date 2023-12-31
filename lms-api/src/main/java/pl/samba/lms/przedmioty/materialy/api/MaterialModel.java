package pl.samba.lms.przedmioty.materialy.api;

import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import pl.samba.lms.przedmioty.materialy.Material;
import pl.samba.lms.przedmioty.przedmioty.api.PrzedmiotyController;

import java.time.LocalDateTime;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Getter
public class MaterialModel extends RepresentationModel<MaterialModel> {
    private final Integer id;
    private final LocalDateTime dataWstawienia;
    private final Integer lp;
    private final String temat;
    private byte[] plik;
    private final String nazwaPliku;
    private final String ext;
    private final String opis;
    private final Boolean widocznosc;

    public MaterialModel(Material m){
        this.id = m.getIdMaterialu();
        this.dataWstawienia = m.getDataWstawienia();
        this.lp = m.getLp();
        this.temat =m.getTemat();
        this.plik = m.getPlik();
        this.nazwaPliku = m.getNazwaPliku();
        this.ext = m.getExt();
        this.opis =m.getOpis();
        this.widocznosc = m.getWidocznosc();

        add(WebMvcLinkBuilder.linkTo(
                methodOn(PrzedmiotyController.class)
                        .get(m.getIdPrzedmiotu())).withRel("przedmiot"));
    }
}
