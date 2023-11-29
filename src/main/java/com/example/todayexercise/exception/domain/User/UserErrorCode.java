package com.example.todayexercise.exception.domain.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum UserErrorCode {
    // 400
    FAIL_TO_LOGIN("아이디 또는 비밀번호가 틀렸습니다.", HttpStatus.BAD_REQUEST.value()),
    NOT_EXIST_USER("존재하지 않는 유저입니다.", HttpStatus.BAD_REQUEST.value()),
    FAIL_TO_UPDATE("업데이트가 실패하였습니다.",HttpStatus.BAD_REQUEST.value()),
    FAIL_TO_DELETE("삭제를 실패하였습니다.",HttpStatus.BAD_REQUEST.value()),

    // 409
    FAIL_TO_SAVE("서버 측의 문제로 데이터 저장에 실패했습니다. 다시 한 번 시도해주세요",HttpStatus.CONFLICT.value()),
    EXIST_EMAIL("이메일이 이미 존재합니다.", HttpStatus.CONFLICT.value()),
    EXIST_NICKNAME("닉네임이 이미 존재합니다.", HttpStatus.CONFLICT.value()),
    NOT_FOUND_MEDIA_FILES("파일을 찾을 수 없다.",HttpStatus.CONFLICT.value());

    private final String description;
    private final Integer status;

    public enum NOT_FOUND_MEDIA_FILES {

    }
}
