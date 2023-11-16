package com.example.todayexercise.exception.domain;

import lombok.Getter;

@Getter
public class CommonResponse {
    private String message;
    private Integer status;

    public CommonResponse(String message, Integer status) {
        this.message = message;
        this.status = status;
    }
}