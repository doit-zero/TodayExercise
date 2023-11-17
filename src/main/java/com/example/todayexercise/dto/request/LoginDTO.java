package com.example.todayexercise.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTO {
    @Schema(description = "이메일 = 로그인 아이디", example = "test@test.com")
    private String email;

    @Schema(description = "비밀번호", example = "test1234")
    private String password;
}
