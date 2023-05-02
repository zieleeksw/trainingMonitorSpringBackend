package com.app.trainingappspringbackend.service;

import com.app.trainingappspringbackend.POJO.UserApp;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.Optional;

;

public interface UserService {
    ResponseEntity<String> signUp(Map<String, String> requestMap);

    ResponseEntity<List<UserApp>> getUsers();

    ResponseEntity<String> login(Map<String, String> requestMap);

    ResponseEntity<Optional<UserApp>> fetchUserById(Long id);

    //boolean deleteUserById(Long id);
    ResponseEntity<String> deleteUserById(Long id);
}
