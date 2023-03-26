package com.app.trainingappspringbackend.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class AppUtils {
    private AppUtils(){

    }
    public static ResponseEntity<String> getResponseEntity(String responseMessage, HttpStatus httpStatus){
        return new ResponseEntity<String>("{\"message\":\""+ responseMessage+"\"}", httpStatus);
    }
}
