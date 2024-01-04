package pl.samba.lms.forum.wpisy.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.samba.lms.forum.wpisy.ForumWpis;
import pl.samba.lms.forum.wpisy.database.ForumWpisRepository;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(path = "/api/forum/wpisy", produces = MediaType.APPLICATION_JSON_VALUE)
public class ForumWpisyController {

    private final ForumWpisRepository forumWpisRepository;

    @Autowired
    public ForumWpisyController(ForumWpisRepository forumWpisRepository) {
        this.forumWpisRepository = forumWpisRepository;
    }

    @GetMapping("/all")
    public ResponseEntity<CollectionModel<ForumWpisModel>> getAllForumWpisy() {
        Iterable<ForumWpis> forumWpisy = forumWpisRepository.findAll();

        if (forumWpisy.iterator().hasNext()) {
            List<ForumWpisModel> listaModeli = new ArrayList<>();

            for (ForumWpis wpis : forumWpisy) {
                listaModeli.add(new ForumWpisModelAssembler().toModel(wpis));
            }

            CollectionModel<ForumWpisModel> kolekcjaModeli = CollectionModel.of(listaModeli);

            kolekcjaModeli.add(WebMvcLinkBuilder.linkTo(methodOn(ForumWpisyController.class).getAllForumWpisy())
                    .withRel("forumWpisy").withTitle("lista_forum_wpisow"));
            return new ResponseEntity<>(kolekcjaModeli, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
