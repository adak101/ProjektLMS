package pl.samba.lms.forum.wpisy.api;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.samba.lms.forum.wpisy.ForumWpis;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/forum/wpisy")
public class ForumWpisyController {
}
