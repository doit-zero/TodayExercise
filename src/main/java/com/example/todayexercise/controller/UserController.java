package com.example.todayexercise.controller;

import com.example.todayexercise.common.CommonResponse;
import com.example.todayexercise.dto.request.Login;
import com.example.todayexercise.dto.request.SingUp;
import com.example.todayexercise.entity.User;
import com.example.todayexercise.exception.domain.CommonErrorCode;
import com.example.todayexercise.exception.domain.CommonException;
import com.example.todayexercise.exception.domain.User.UserErrorCode;
import com.example.todayexercise.exception.domain.User.UserException;
import com.example.todayexercise.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
@Tag(name = "유저", description = "유저 관련 API")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public CommonResponse<String> signup(@RequestBody SingUp signup) {
        return CommonResponse.success(userService.signup(signup));
    }

    @PostMapping("/login")
    public CommonResponse<String> login(@RequestBody Login login , HttpSession session) {
        return CommonResponse.success(userService.login(login,session));
    }

    @PostMapping("/logout")
    public CommonResponse<String> logout(HttpSession session, @AuthenticationPrincipal User user) {
        if(session == null) throw new CommonException(CommonErrorCode.FAIL_TO_SAVE);
        return CommonResponse.success(userService.logout(session,user));
    }


}
