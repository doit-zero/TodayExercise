package com.example.todayexercise.exception.domain;

import com.example.todayexercise.common.CommonResponse;
import com.example.todayexercise.exception.domain.User.UserException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CommonException.class)
    public CommonResponse<String> handleCommonException(CommonException commonException) {
        return CommonResponse.error(commonException.getErrorCode(),commonException.getMessage());
    }

    @ExceptionHandler(UserException.class)
    public CommonResponse<String> handleUserException(UserException userException) {
        return CommonResponse.error(userException.getErrorCode(),userException.getErrorMessage());
    }
}
