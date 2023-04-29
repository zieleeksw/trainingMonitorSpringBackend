package com.app.trainingappspringbackend.controllers;
import com.app.trainingappspringbackend.POJO.UserApp;
import org.apache.catalina.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequestMapping(path = "/user")
public interface UserController {
    @PostMapping(path = "/signup")
    ResponseEntity<String> signUp(@RequestBody Map<String, String> requestMap);
    @PostMapping(path = "/login")
    ResponseEntity<String> login(@RequestBody Map<String, String> requestMap);
    @GetMapping()
    ResponseEntity<List<UserApp>> getUsers();
    @GetMapping("/{id}")
    ResponseEntity<Optional<UserApp>> fetchUserById(@PathVariable("id") Long id);
    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteUserById(@PathVariable("id") Long id);
}
