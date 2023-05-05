package com.app.trainingappspringbackend.serviceImpl;

import com.app.trainingappspringbackend.DAO.UserDao;
import com.app.trainingappspringbackend.POJO.UserApp;
import com.app.trainingappspringbackend.constants.AppConstants;
import com.app.trainingappspringbackend.security.JwtAuthenticationFilter;
import com.app.trainingappspringbackend.service.UserService;
import com.app.trainingappspringbackend.utils.AppUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    private final JwtAuthenticationFilter jwtAuthenticationFilter;


    @Override
    public ResponseEntity<Optional<UserApp>> fetchUserById(Long id) {
        try {
            Optional<UserApp> optional = userDao.findById(id);
            if (optional.isEmpty()) {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            } else {
                return new ResponseEntity<>(optional, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> deleteUserById(Long id) {
        try {
            if (jwtAuthenticationFilter.isAdmin()) {
                if (userDao.existsById(id)) {
                    userDao.deleteById(id);
                    return AppUtils.getResponseEntity("User with id: " + id + " deleted successfully", HttpStatus.OK);
                } else
                    return AppUtils.getResponseEntity(AppConstants.INVALID_ID, HttpStatus.NOT_FOUND);
            } else
                return AppUtils.getResponseEntity(AppConstants.NO_PERMISSION, HttpStatus.FORBIDDEN);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return AppUtils.getResponseEntity(AppConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<UserApp>> getUsers() {
        try {
            if (jwtAuthenticationFilter.isUser() || jwtAuthenticationFilter.isAdmin()) {
                return new ResponseEntity<>(userDao.findAll(), HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}