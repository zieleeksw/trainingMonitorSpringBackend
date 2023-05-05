package com.app.trainingappspringbackend.controllersImpl;

import com.app.trainingappspringbackend.constants.AppConstants;
import com.app.trainingappspringbackend.controllers.AccountController;
import com.app.trainingappspringbackend.service.AccountService;
import com.app.trainingappspringbackend.utils.AppUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AccountControllerImpl implements AccountController {
    private final AccountService accountService;

    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        try {
            return accountService.signUp(requestMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return AppUtils.getResponseEntity(AppConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> login(Map<String, String> requestMap) {
        try {
            return accountService.login(requestMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return AppUtils.getResponseEntity(AppConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> changePassword(Map<String, String> requestMap) {
        try {
            return accountService.changePassword(requestMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return AppUtils.getResponseEntity(AppConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
