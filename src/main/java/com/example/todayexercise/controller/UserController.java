package com.example.todayexercise.controller;

import com.example.todayexercise.dto.request.SingUp;
import com.example.todayexercise.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
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

    @GetMapping
    public String test(){
        return "test";
    }

}
