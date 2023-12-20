package com.iqbaal.restful.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.iqbaal.restful.dto.response.APIResponse;
import com.iqbaal.restful.exception.ProvinceNotFoundException;
import com.iqbaal.restful.exception.RegencyNotFoundException;
import com.iqbaal.restful.exception.TutorialNotFoundException;

@RestControllerAdvice
public class ErrorController {

    @ExceptionHandler(TutorialNotFoundException.class)
    public ResponseEntity<APIResponse<Object>> tutorialNotFoundException(TutorialNotFoundException exception){
        return new ResponseEntity<APIResponse<Object>>(new APIResponse<>("Failed", 404, exception.getMessage(), null), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProvinceNotFoundException.class)
    public ResponseEntity<APIResponse<Object>> provinceNotFoundException(ProvinceNotFoundException exception){
        return new ResponseEntity<APIResponse<Object>>(new APIResponse<>("Failed", 404, exception.getMessage(), null), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RegencyNotFoundException.class)
    public ResponseEntity<APIResponse<Object>> regencyNotFoundException(RegencyNotFoundException exception){
        return new ResponseEntity<APIResponse<Object>>(new APIResponse<>("Failed", 404, exception.getMessage(), null), HttpStatus.NOT_FOUND);
    }
}
