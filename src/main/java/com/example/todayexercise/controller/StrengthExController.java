package com.example.todayexercise.controller;

import com.example.todayexercise.common.CommonResponse;
import com.example.todayexercise.entity.User;
import com.example.todayexercise.service.StrengthExService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Tag(name = "무산소 운동", description = "무산소운동 관련 API")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/strength")
public class StrengthExController {

}
