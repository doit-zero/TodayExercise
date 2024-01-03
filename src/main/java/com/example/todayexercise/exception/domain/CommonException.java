package com.example.todayexercise.exception.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.HashSet;

@Getter
@Setter
public class CommonException extends RuntimeException{
    private Integer errorCode;
    private String errorMessage;

    public CommonException(CommonErrorCode commonErrorCode) {
        this.errorCode = commonErrorCode.getStatus();
        this.errorMessage = commonErrorCode.getDescription();
    }
}
