package com.app.trainingappspringbackend.service;;
import com.app.trainingappspringbackend.POJO.User;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface UserService {
    ResponseEntity<String> signUp(Map<String, String> requestMap);
    List<User> getUsers();

}
