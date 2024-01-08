package com.example.todayexercise.exception.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum CommonErrorCode {
    // 400
    INVALID_FORMAT_FILE("올바른 파일 이름이 아닙니다", HttpStatus.BAD_REQUEST.value()),
    NOT_FOUND_IMG_FILES("이미지 파일이 등록 되지 않았습니다.", HttpStatus.BAD_REQUEST.value()),
    NOT_IMAGE_EXTENSION("파일 확장자가 없습니다.", HttpStatus.BAD_REQUEST.value()),
    FAIL_TO_UPDATE("업데이트가 실패하였습니다.",HttpStatus.BAD_REQUEST.value()),
    FAIL_TO_DELETE("삭제를 실패하였습니다.",HttpStatus.BAD_REQUEST.value()),

    // 409
    FAIL_TO_SAVE("서버 측의 문제로 데이터 저장에 실패했습니다. 다시 한 번 시도해주세요",HttpStatus.CONFLICT.value()),
    NOT_FOUND_MEDIA_FILES("파일을 찾을 수 없다.",HttpStatus.CONFLICT.value());

    private final String description;
    private final Integer status;


}
