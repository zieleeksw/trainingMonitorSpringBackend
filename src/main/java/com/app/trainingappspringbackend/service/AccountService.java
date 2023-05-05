package com.app.trainingappspringbackend.service;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface AccountService {
    ResponseEntity<String> signUp(Map<String, String> requestMap);

    ResponseEntity<String> login(Map<String, String> requestMap);

    ResponseEntity<String> changePassword(Map<String, String> requestMap);


}
