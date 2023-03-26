package com.app.trainingappspringbackend.serviceImpl;

import com.app.trainingappspringbackend.DAO.UserDao;
import com.app.trainingappspringbackend.POJO.Role;
import com.app.trainingappspringbackend.POJO.User;
import com.app.trainingappspringbackend.constants.AppConstants;
import com.app.trainingappspringbackend.service.UserService;
import com.app.trainingappspringbackend.utils.AppUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;


    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        log.info("inside signup {}", requestMap);
        try {
            if (validateSignUpMap(requestMap)) {
                User user = userDao.findByEmail(requestMap.get("email"));
                if (Objects.isNull(user)) {
                    userDao.save(getUserFromMap(requestMap));
                    return AppUtils.getResponseEntity(AppConstants.SUCCESSFULLY_REGISTERED, HttpStatus.OK);
                } else {
                    return AppUtils.getResponseEntity(AppConstants.EMAIL_ALREADY_EXISTS, HttpStatus.BAD_REQUEST);
                }
            } else {
                return AppUtils.getResponseEntity(AppConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return AppUtils.getResponseEntity(AppConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private boolean validateSignUpMap(Map<String, String> requestMap) {
        return requestMap.containsKey("username") && requestMap.containsKey("name") && requestMap.containsKey("email") && requestMap.containsKey("password") && requestMap.containsKey("dob") && requestMap.containsKey("gender") && requestMap.containsKey("height") && requestMap.containsKey("currentWeight") && requestMap.containsKey("targetWeight") && requestMap.containsKey("weightPerWeek");
    }

    private User getUserFromMap(Map<String, String> requestMap) {
        User user = new User();
        user.setName(requestMap.get("name"));
        user.setUsername(requestMap.get("username"));
        user.setEmail(requestMap.get("email"));
        user.setPassword(requestMap.get("password"));
        user.setDob(LocalDate.parse(requestMap.get("dob")));
        user.setGender(requestMap.get("gender"));
        user.setRole(Role.USER);
        user.setHeight(Integer.parseInt(requestMap.get("height")));
        user.setCurrentWeight(Double.parseDouble(requestMap.get("currentWeight")));
        user.setTargetWeight(Double.parseDouble(requestMap.get("targetWeight")));
        user.setWeightPerWeek(Double.parseDouble(requestMap.get("weightPerWeek")));
        user.setActivityLevel(requestMap.get("activityLevel"));
        return user;
    }

    @Override
    public List<User> getUsers() {
        return userDao.findAll();
    }

}
