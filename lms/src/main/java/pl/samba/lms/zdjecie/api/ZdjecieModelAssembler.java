package pl.samba.lms.zdjecie.api;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import pl.samba.lms.uzytkownicy.Uzytkownik;
import pl.samba.lms.uzytkownicy.api.UzytkownicyController;
import pl.samba.lms.uzytkownicy.api.UzytkownikModel;
import pl.samba.lms.zdjecie.Zdjecie;

public class ZdjecieModelAssembler extends RepresentationModelAssemblerSupport<Zdjecie, ZdjecieModel> {
    public ZdjecieModelAssembler(){
        super(ZdjeciaController.class, ZdjecieModel.class);
    }

    @Override
    protected ZdjecieModel instantiateModel(Zdjecie entity) {
        return new ZdjecieModel(entity);
    }
    @Override
    public ZdjecieModel toModel(Zdjecie entity) {
        return createModelWithId(entity.getIdZdjecia(), entity);
    }
}
