package com.example.todayexercise.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserUpdateDTO {
    @Schema(description = "비밀번호", example = "test1234")
    private String password;

    @Schema(description = "로그인 후 보여지는 유저 아이디", example = "DoIt")
    private String nickName;
}
