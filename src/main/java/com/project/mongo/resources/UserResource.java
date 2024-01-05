package com.project.mongo.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.mongo.domain.User;
import com.project.mongo.dto.UserDto;
import com.project.mongo.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<List<UserDto>> findAll() {
        List<User> users = service.findAll();
        List<UserDto> usersDto = users.stream().map(user -> new UserDto(user)).collect(Collectors.toList());
        return ResponseEntity.ok().body(usersDto);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable String id) {
        User user = service.findById(id);
        return ResponseEntity.ok().body(new UserDto(user));
    }
}
