package com.app.trainingappspringbackend.controllers;

import com.app.trainingappspringbackend.POJO.UserApp;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@RequestMapping(path = "/user")
public interface UserController {
    @GetMapping()
    ResponseEntity<List<UserApp>> getUsers();

    @GetMapping("/{id}")
    ResponseEntity<Optional<UserApp>> fetchUserById(@PathVariable("id") Long id);

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteUserById(@PathVariable("id") Long id);
}
