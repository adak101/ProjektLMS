package pl.samba.lms.uzytkownicy.api;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.samba.lms.utils.api.ControllerInterface;
import pl.samba.lms.uzytkownicy.Uzytkownik;
import pl.samba.lms.uzytkownicy.database.UzytkownikRepository;

import java.util.LinkedList;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(path = "/api/uzytkownik", produces = "application/json")
public class UzytkownicyController implements ControllerInterface<Uzytkownik, UzytkownikModel> {

    private final UzytkownikRepository dataSet;

    public UzytkownicyController(UzytkownikRepository dataSet){
        this.dataSet = dataSet;
    }
    @GetMapping
    @Override
    public CollectionModel<Object> getAllEndPoints() {
        return null;
    }

    @GetMapping("/all")
    @Override
    public ResponseEntity<CollectionModel<UzytkownikModel>> get(
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) String direction,
            @RequestParam(required = false) Integer size,
            @RequestParam(required = false) Integer page
    ) {
        Iterable<Uzytkownik> users;

        if(sort != null || direction != null || size != null || page != null ){
            String params = sort + ";" +
                    direction + ";" +
                    (size == null? "0" : size) + ";" +
                    (page == null? "0" : page);
            users = dataSet.getAll(params);
        }
        else users = dataSet.getAll();


        if(users.iterator().hasNext()){
            LinkedList<UzytkownikModel> userModelsList = new LinkedList<>();

            for (Uzytkownik user :
                    users) {
                userModelsList.add(new UzytkownikModelAssembler().toModel(user));
            }

            CollectionModel<UzytkownikModel> usersModel = CollectionModel.of(userModelsList);
            usersModel.add(WebMvcLinkBuilder
                    .linkTo(methodOn(UzytkownicyController.class).get(sort, direction,size, page))
                    .withRel("uzytkownicy").withTitle("lista_uzytkownikow"));

            return new ResponseEntity<>(usersModel, HttpStatus.OK);
        }
        else return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<UzytkownikModel> get(@PathVariable("id") Integer id) {
        Optional<Uzytkownik> optUzytkownik = Optional.ofNullable(dataSet.getById(id));
        if(optUzytkownik.isPresent()){
            UzytkownikModel um = new UzytkownikModelAssembler().toModel(optUzytkownik.get());
            return new ResponseEntity<>(um, HttpStatus.OK);
        }
        else return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code=HttpStatus.NO_CONTENT)
    @Override
    public void delete(@PathVariable("id") Integer id) {
        dataSet.delete(id);
    }
    @PostMapping(consumes = "application/json")
    @Override
    public ResponseEntity<Object> post(@RequestBody Uzytkownik data) {
        Integer id = dataSet.save(data);
        return new ResponseEntity<>(
                EntityModel.of(WebMvcLinkBuilder
                        .linkTo(methodOn(UzytkownicyController.class).get(id))
                        .withRel("nowy_uzytkownik").withTitle("nowy_uzytkownik")),
                HttpStatus.CREATED);
    }

    @PutMapping(consumes = "application/json")
    @Override
    public Uzytkownik put(@RequestBody Uzytkownik data) {
        return null;
    }

    @PatchMapping(path="/{id}", consumes = "application/json")
    @Override
    public Uzytkownik patch(
            @PathVariable("id") Integer id,
            @RequestBody Uzytkownik data) {
        return null;
    }
}
