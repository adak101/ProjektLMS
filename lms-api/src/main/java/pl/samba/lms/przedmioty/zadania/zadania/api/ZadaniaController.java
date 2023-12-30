package pl.samba.lms.przedmioty.zadania.zadania.api;

import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.samba.lms.przedmioty.zadania.zadania.Zadanie;
import pl.samba.lms.przedmioty.zadania.zadania.database.ZadanieRepository;
import pl.samba.lms.utils.PathType;
import pl.samba.lms.utils.api.ControllerInterface;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(path= PathType.ZADANIE, produces= MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class ZadaniaController implements ControllerInterface<Zadanie, ZadanieModel> {

    private final ZadanieRepository dataSet;

    /**
     * Metoda do pobierania zadań dla konkretnego przedmiotu z uwzględnieniem parametrów
     * takich jak sortowanie, rozmiar strony
     *
     * @param kod Kod przedmiotu w base64
     * @param size Liczba wyników na stronie.
     * @param page Numer strony, liczony od 0.
     * @return Odpowiedź HTTP zawierająca kolekcję modelu zasobów.
     */
    @GetMapping(PathType.ALL)
    public ResponseEntity<CollectionModel<ZadanieModel>> get(
            @RequestParam(required = true) String kod,
            @RequestParam(required = false) Integer size,
            @RequestParam(required = false) Integer page
    ) {
        String decodedKod = new String(Base64.getDecoder().decode(kod));

        String params;
        if(size == null && page == null){
            params = ";;" + decodedKod;
        }
        else{
            params = (size == null? "0" : size) + ";" +
                    (page == null? "0" : page) + ";" +
                    decodedKod;
        }
        Iterable<Zadanie> zadania = dataSet.getAll(params);

        if(zadania.iterator().hasNext()){
            List<ZadanieModel> listaModeli = new ArrayList<>();

            for(Zadanie przedmiot: zadania){
                listaModeli.add(new ZadanieModelAssembler().toModel(przedmiot));
            }

            CollectionModel<ZadanieModel> kolekcjaModeli = CollectionModel.of(listaModeli);

            kolekcjaModeli.add(WebMvcLinkBuilder.linkTo(methodOn(ZadaniaController.class).get(kod,size, page))
                    .withRel("zadania").withTitle("lista_zadan"));
            return new ResponseEntity<>(kolekcjaModeli, HttpStatus.OK);
        }
        else return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Deprecated
    @Override
    public ResponseEntity<CollectionModel<ZadanieModel>> get(Integer size, Integer page) {
        // BRAK WSPARCIA
        return null;
    }

    @GetMapping(PathType.ID)
    @Override
    public ResponseEntity<ZadanieModel> get(@PathVariable("id") Integer id) {
        Optional<Zadanie> optZadanie = Optional.ofNullable(dataSet.getById(id));

        if(optZadanie.isPresent()){
            ZadanieModel model = new ZadanieModelAssembler().toModel(optZadanie.get());
            return new ResponseEntity<>(model, HttpStatus.OK);
        }
        else return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(PathType.ID)
    @Override
    public void delete(@PathVariable("id") Integer id) {
        dataSet.delete(id);
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public ResponseEntity<Object> post(@RequestBody Zadanie data) {
        Integer id = dataSet.save(data);
        return new ResponseEntity<>(
                EntityModel.of(WebMvcLinkBuilder.
                        linkTo(methodOn(ZadaniaController.class).get(id))
                        .withRel("nowe")
                        .withTitle("zadanie")),
                HttpStatus.CREATED);
    }

    @Override

    @PatchMapping(path=PathType.ID, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> patch(
            @PathVariable("id") Integer id,
            @RequestBody Zadanie data
    ) {
        Zadanie current = dataSet.getById(id);

        if(data.getDataPoczatku() != null){
            current.setDataPoczatku(data.getDataPoczatku());
        }
        if(data.getDataKonca() != null){
            current.setDataKonca(data.getDataKonca());
        }
        if(data.getTresc() != null){
            current.setTresc(data.getTresc());
        }

        id = dataSet.update(current);
        return new ResponseEntity<>(
                EntityModel.of(WebMvcLinkBuilder.
                        linkTo(methodOn(ZadaniaController.class).get(id))
                        .withRel("zaktualizowane")
                        .withTitle("zadanie")),
                HttpStatus.OK);
    }
}
