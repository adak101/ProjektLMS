package pl.samba.lms.zdjecie.api;

import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import pl.samba.lms.zdjecie.Zdjecie;

@Getter
public class ZdjecieModel extends RepresentationModel<ZdjecieModel> {
        private final byte[] zdjecie;
        private final String nazwa;
        private final String ext;
        private final String alt;

        public ZdjecieModel(Zdjecie z){
            this.zdjecie = z.getZdjecie();
            this.nazwa = z.getNazwa();
            this.ext =z.getExt();
            this.alt = z.getAlt();
        }

}
