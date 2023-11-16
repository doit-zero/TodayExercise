package com.example.todayexercise.exception.domain;

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
}
