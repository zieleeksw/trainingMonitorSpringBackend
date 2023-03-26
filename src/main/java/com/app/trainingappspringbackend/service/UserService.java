package com.app.trainingappspringbackend.service;;
import com.app.trainingappspringbackend.POJO.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

public interface UserService {
    ResponseEntity<String> signUp(Map<String, String> requestMap);

    List<User> getUsers();
}
