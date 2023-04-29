package com.app.trainingappspringbackend.controllersImpl;

import com.app.trainingappspringbackend.POJO.UserApp;
import com.app.trainingappspringbackend.constants.AppConstants;
import com.app.trainingappspringbackend.controllers.UserController;
import com.app.trainingappspringbackend.service.UserService;
import com.app.trainingappspringbackend.utils.AppUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {
    private final UserService userService;

    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        try {
            return userService.signUp(requestMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return AppUtils.getResponseEntity(AppConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @Override
    public ResponseEntity<String> login(Map<String, String> requestMap) {
        try{
            return userService.login(requestMap);
        } catch (Exception e){
            e.printStackTrace();
        }
        return AppUtils.getResponseEntity(AppConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<UserApp>> getUsers() {
        try{
            return userService.getUsers();
        } catch (Exception e){
            e.printStackTrace();
        }
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<Optional<UserApp>> fetchUserById(Long id) {
        try {
            return userService.fetchUserById(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> deleteUserById(Long id) {
        try{
            userService.deleteUserById(id);
        } catch (Exception e){
            e.printStackTrace();
        }
        return AppUtils.getResponseEntity("CHIJ", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
