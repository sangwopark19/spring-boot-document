package com.in28minutes.rest.webservices.restfulwebservices.user;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

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
import java.util.stream.Collectors;

@RestController
public class UserResource {

    private final UserDaoService service;

    @Autowired
    public UserResource(UserDaoService service) {
        this.service = service;
    }

    @GetMapping("/users")
    public List<EntityModel<User>> retrieveAllUsers() {
        return service.findAll().stream().map(user -> {
            // Java Stream 을 이용하여 각 user 객체의 엔티티 모델 생성
            EntityModel<User> entityModel = EntityModel.of(user);
            // WebMvcLinkBuilder 로 현재 컨트롤러의 메서드 uri를 가져옴
            WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveUser(user.getId()));
            // detail이라는 이름으로 링크를 entityModel에 추가
            entityModel.add(link.withRel("detail"));
            return entityModel;
            // stream 의 Collectors.toList로 리스트로 변환
        }).collect(Collectors.toList());
    }


    @GetMapping("/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable int id) {
        User user = service.findById(id);

        if (user == null) {
            throw new UserNotFoundException("id:" + id);
        }
        EntityModel<User> entityModel = EntityModel.of(user);
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        entityModel.add(link.withRel("all-users"));
        return entityModel;
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {
        service.deleteById(id);
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User savedUser = service.save(user);

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
