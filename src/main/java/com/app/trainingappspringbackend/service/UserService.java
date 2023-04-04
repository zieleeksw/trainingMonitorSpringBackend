package com.app.trainingappspringbackend.service;;
import com.app.trainingappspringbackend.POJO.UserApp;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface UserService {
    ResponseEntity<String> signUp(Map<String, String> requestMap);
    List<UserApp> getUsers();
    ResponseEntity<String> login(Map<String, String> requestMap);

}
