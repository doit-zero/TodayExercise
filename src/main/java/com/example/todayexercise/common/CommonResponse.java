package com.example.todayexercise.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
@Getter
@RequiredArgsConstructor
public class CommonResponse<T> {
    private final Integer status;
    private final String message;
    private final T data;

    public static <T> CommonResponse<T> success(T data) {
        return new CommonResponse<>(200, "Success", data);
    }

    public static <T> CommonResponse<T> error(int status, String message) {
        return new CommonResponse<>(status, message, null);
    }

}