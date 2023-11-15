package com.example.todayexercise.controller;

import com.example.todayexercise.dto.request.SingUp;
import com.example.todayexercise.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @GetMapping
    public String test(){
        return "ddd";
    }

    @PostMapping("/signup")
    public String signup(@RequestBody SingUp signup) {
        return userService.signup(signup);
    }
}
