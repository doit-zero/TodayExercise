package com.example.todayexercise.exception.domain.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserException extends RuntimeException {
    private Integer errorCode;
    private String errorMessage;

    public UserException(UserErrorCode userErrorCode) {
        this.errorCode = userErrorCode.getStatus();
        this.errorMessage = userErrorCode.getDescription();
    }
}
