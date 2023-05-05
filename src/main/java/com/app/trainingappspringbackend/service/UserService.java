package com.app.trainingappspringbackend.service;

import com.app.trainingappspringbackend.POJO.UserApp;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {
    ResponseEntity<List<UserApp>> getUsers();

    ResponseEntity<Optional<UserApp>> fetchUserById(Long id);

    ResponseEntity<String> deleteUserById(Long id);

}
