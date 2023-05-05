package com.app.trainingappspringbackend.serviceImpl;

import com.app.trainingappspringbackend.DAO.UserDao;
import com.app.trainingappspringbackend.POJO.Role;
import com.app.trainingappspringbackend.POJO.UserApp;
import com.app.trainingappspringbackend.constants.AppConstants;
import com.app.trainingappspringbackend.security.JwtAuthenticationFilter;
import com.app.trainingappspringbackend.security.JwtService;
import com.app.trainingappspringbackend.service.AccountService;
import com.app.trainingappspringbackend.utils.AppUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;


@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        log.info("inside signup {}", requestMap);
        try {
            if (validateSignUpMap(requestMap)) {
                Optional<UserApp> user = userDao.findByEmail(requestMap.get("email"));
                if (!user.isPresent()) {
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
        return requestMap.containsKey("name") &&
                requestMap.containsKey("email") &&
                requestMap.containsKey("password") &&
                requestMap.containsKey("dob") &&
                requestMap.containsKey("gender") &&
                requestMap.containsKey("height") &&
                requestMap.containsKey("currentWeight") &&
                requestMap.containsKey("targetWeight") &&
                requestMap.containsKey("weightPerWeek");
    }

    private UserApp getUserFromMap(Map<String, String> requestMap) {
        UserApp userApp = UserApp.builder()
                .name(requestMap.get("name"))
                .email(requestMap.get("email"))
                .password(passwordEncoder.encode(requestMap.get("password")))
                .dob(LocalDate.parse(requestMap.get("dob")))
                .gender(requestMap.get("gender"))
                .role(Role.USER)
                .height(Integer.parseInt(requestMap.get("height")))
                .currentWeight(Double.parseDouble(requestMap.get("currentWeight")))
                .targetWeight(Double.parseDouble(requestMap.get("targetWeight")))
                .weightPerWeek(Double.parseDouble(requestMap.get("weightPerWeek")))
                .activityLevel(requestMap.get("activityLevel"))
                .build();
        return userApp;
    }

    @Override
    public ResponseEntity<String> login(Map<String, String> requestMap) {
        log.info("Inside login " + requestMap);
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(requestMap.get("email"), requestMap.get("password"))
            );
            var user = userDao.findByEmail(requestMap.get("email")).orElseThrow();
            var jwtToken = jwtService.generateToken(user);
            return new ResponseEntity<>("{\"token\":\"" + jwtToken + "\"}", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return AppUtils.getResponseEntity(AppConstants.LOGIN_OR_PASSWORD_INVALID, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> changePassword(Map<String, String> requestMap) {
        try {
            Optional<UserApp> optional = userDao.findByEmail(jwtAuthenticationFilter.getCurrentUserEmail());
            if (optional.isPresent()) {
                UserApp user = optional.get();
                if (passwordEncoder.matches(requestMap.get("oldPassword"), user.getPassword())) {
                    user.setPassword(passwordEncoder.encode(requestMap.get("newPassword")));
                    userDao.save(user);
                    return AppUtils.getResponseEntity("Password changed successfully", HttpStatus.OK);
                } else
                    return AppUtils.getResponseEntity("Incorrect old password", HttpStatus.BAD_REQUEST);
            }
            return AppUtils.getResponseEntity(AppConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return AppUtils.getResponseEntity(AppConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
