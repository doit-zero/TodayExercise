package com.example.todayexercise.exception.domain;

import com.example.todayexercise.exception.domain.User.UserException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CommonException.class)
    public ResponseEntity<CommonResponse>  handleCommonException(CommonException commonException) {
        CommonResponse response = new CommonResponse(commonException.getErrorMessage(),commonException.getErrorCode());
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }

    @ExceptionHandler(UserException.class)
    public ResponseEntity<CommonResponse>  handleUserException(UserException userException) {
        CommonResponse response = new CommonResponse(userException.getErrorMessage(),userException.getErrorCode());
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }
}
