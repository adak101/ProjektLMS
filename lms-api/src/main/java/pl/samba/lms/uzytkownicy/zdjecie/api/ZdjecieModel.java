package pl.samba.lms.uzytkownicy.zdjecie.api;

import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import pl.samba.lms.uzytkownicy.zdjecie.Zdjecie;

@Getter
public class ZdjecieModel extends RepresentationModel<ZdjecieModel> {
        private final byte[] plik;
        private final String nazwa;
        private final String ext;
        private final String alt;

        public ZdjecieModel(Zdjecie z){
            this.plik = z.getPlik();
            this.nazwa = z.getNazwa();
            this.ext =z.getExt();
            this.alt = z.getAlt();
        }

}
