package com.app.trainingappspringbackend.controllersImpl;

import com.app.trainingappspringbackend.POJO.User;
import com.app.trainingappspringbackend.constants.AppConstants;
import com.app.trainingappspringbackend.controllers.UserController;
import com.app.trainingappspringbackend.service.UserService;
import com.app.trainingappspringbackend.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class UserControllerImpl implements UserController {

    @Autowired
    UserService userService;

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
    public List<User> getUsers() {
        return userService.getUsers();
    }
}
