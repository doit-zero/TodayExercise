package com.example.todayexercise.controller;

import com.example.todayexercise.common.CommonResponse;
import com.example.todayexercise.dto.request.Login;
import com.example.todayexercise.dto.request.SingUp;
import com.example.todayexercise.dto.request.Update;
import com.example.todayexercise.entity.User;
import com.example.todayexercise.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
@Tag(name = "유저", description = "유저 관련 API")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;


    @Operation(summary = "회원가입")
    @PostMapping("/signup")
    public CommonResponse<String> signup(@RequestBody SingUp signup) {
        return CommonResponse.success(userService.signup(signup));
    }
    @Operation(summary = "로그인")
    @PostMapping("/login")
    public CommonResponse<String> login(@RequestBody Login login , HttpSession session) {
        return CommonResponse.success(userService.login(login,session));
    }

    @Operation(summary = "로그아웃")
    @PostMapping("/logout")
    public CommonResponse<String> logout(HttpSession session, @AuthenticationPrincipal User user) {
        return CommonResponse.success(userService.logout(session,user));
    }

    @Operation(summary = "회원정보 변경")
    @PutMapping("/update")
    public CommonResponse<String> update(@AuthenticationPrincipal User user, Update update) {
        return CommonResponse.success(userService.update(user,update));
    }

    @Operation(summary = "eamil 중복확인")
    @PostMapping("/check/email/{email}")
    public CommonResponse<String> checkEmail(@PathVariable String email) {
        return CommonResponse.success(userService.checkEmail(email));
    }

    @Operation(summary = "nickName 중복확인")
    @PostMapping("/check/nickName/{nickName}")
    public CommonResponse<String> checkNickName(@PathVariable String nickName) {
        return CommonResponse.success(userService.checkNickName(nickName));
    }


}
