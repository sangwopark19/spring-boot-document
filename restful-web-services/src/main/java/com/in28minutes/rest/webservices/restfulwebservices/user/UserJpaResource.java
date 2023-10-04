package com.in28minutes.rest.webservices.restfulwebservices.user;


import com.in28minutes.rest.webservices.restfulwebservices.jpa.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/jpa")
public class UserJpaResource {

    private final UserRepository repository;


    @Autowired
    public UserJpaResource(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return repository.findAll();
    }


    @GetMapping("/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable int id) {
        Optional<User> user = repository.findById(id);

        if (user.isEmpty()) {
            throw new UserNotFoundException("id:" + id);
        }
        EntityModel<User> entityModel = EntityModel.of(user.get());
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        entityModel.add(link.withRel("all-users"));
        return entityModel;
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {
        repository.deleteById(id);
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User savedUser = repository.save(user);

        //ResponseEntity.created() 에 줄 매개변수
        //ServletUriComponentsBuilder.fromCurrentRequest 는 현재 메소드의 경로를가져온다 ex. /users
        //path()는 추가할 path를 설정한다. ex. /users/{id}
        //buildAndExpand()는 path에서 설정할 {id}를 유동적으로 방금 생성한 사용자의 getId로 바꿔준다. ex. /users/id{4}
        //toUri로 uri형식으로 바꿔준다.
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        // ResponseEntity.created()는 http 201을 반환한다
        //그리고 매개변수로 uri 를 주면 해당 uri를 사용자에게 보내준다.
        return ResponseEntity.created(location).build();
    }


}
