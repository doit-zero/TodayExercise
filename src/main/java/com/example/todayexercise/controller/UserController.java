package com.example.todayexercise.controller;

import com.example.todayexercise.dto.request.Login;
import com.example.todayexercise.dto.request.SingUp;
import com.example.todayexercise.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
@Tag(name = "유저", description = "유저 관련 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public String signup(@RequestBody SingUp signup) {
        return userService.signup(signup);
    }

    @PostMapping("/login")
    public String login(@RequestBody Login login , HttpSession session) {
        return userService.login(login,session);
    }



    //레디스 연동 확인용
//    @PostMapping("/test")
//    public String testRedis(@RequestBody Login login){
//        redisTemplate.opsForValue().set(login.getEmail(),login.getPassword());
//        return "dd";
//    }

}
