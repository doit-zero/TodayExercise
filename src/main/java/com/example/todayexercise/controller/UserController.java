package com.example.todayexercise.controller;

import com.example.todayexercise.dto.request.SingUp;
import com.example.todayexercise.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public String signup(@RequestBody SingUp signup) {
        return userService.signup(signup);
    }
}
