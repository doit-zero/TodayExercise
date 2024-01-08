package com.example.todayexercise.controller;

import com.example.todayexercise.common.CommonResponse;
import com.example.todayexercise.dto.request.LoginDTO;
import com.example.todayexercise.dto.request.SingUpDTO;
import com.example.todayexercise.entity.User;
import com.example.todayexercise.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Tag(name = "유저", description = "유저 관련 API")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;


    @Operation(summary = "회원가입")
    @PostMapping("/signup")
    public CommonResponse<String> signup(@RequestBody SingUpDTO signup) {
        return CommonResponse.success(userService.signup(signup));
    }
    @Operation(summary = "로그인")
    @PostMapping("/login")
    public CommonResponse<String> login(@RequestBody LoginDTO loginDTO, HttpSession session) {
        return CommonResponse.success(userService.login(loginDTO,session));
    }

    @Operation(summary = "로그아웃")
    @PostMapping("/logout")
    public CommonResponse<String> logout(HttpSession session, @AuthenticationPrincipal User user) {
        return CommonResponse.success(userService.logout(session,user));
    }

    @Operation(summary = "닉네임 변경")
    @PutMapping(value = "/update/nickName")
    public CommonResponse<String> updateNickName(@AuthenticationPrincipal User user,
                                         @RequestParam(value = "nickName",required = false) String nickName) {
        return CommonResponse.success(userService.updateNickName(user,nickName));
    }

    @Operation(summary = "비밀번호 변경")
    @PutMapping(value = "/update/password")
    public CommonResponse<String> updatePassword(@AuthenticationPrincipal User user,
                                         @RequestParam(value = "oldPassword",required = false) String oldPassword,
                                                 @RequestParam(value = "newPassword",required = false) String newPassword) {
        return CommonResponse.success(userService.updatePassword(user,oldPassword,newPassword));
    }

    @Operation(summary = "프로필 이미지 수정")
    @PostMapping(value = "/update/profileImage",consumes = "multipart/form-data")
    public CommonResponse<?> updateImage(@AuthenticationPrincipal User user,
                                         @RequestParam("imageFile") MultipartFile imageFile) {
        return CommonResponse.success(userService.updateImage(user,imageFile));
    }


    @Operation(summary = "eamil 중복확인")
    @PostMapping("/check/email/{email}")
    public CommonResponse<String> checkEmail(@Parameter(name = "email") @PathVariable String email) {
        return CommonResponse.success(userService.checkEmail(email));
    }

    @Operation(summary = "nickName 중복확인")
    @PostMapping("/check/nickname/{nickName}")
    public CommonResponse<String> checkNickName(@Parameter(name = "nickName") @PathVariable String nickName) {
        return CommonResponse.success(userService.checkNickName(nickName));
    }

    @Operation(summary = "로그인 상태 체크")
    @PostMapping("/check")
    public CommonResponse<String> checkState (@AuthenticationPrincipal User user) {
        if(user != null) {
            return CommonResponse.success("로그인 유지");
        } else
            return CommonResponse.error(401 ,"로그인 필요");
    }

    @Operation(summary = "사진,닉네임 불러오기")
    @GetMapping("/info")
    public CommonResponse <Map<String,String>>  getInfo(@AuthenticationPrincipal User user) {
        return CommonResponse.success(userService.getInfo(user));
    }

}
