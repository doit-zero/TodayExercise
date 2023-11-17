package com.example.todayexercise.controller;

import com.example.todayexercise.common.CommonResponse;
import com.example.todayexercise.entity.User;
import com.example.todayexercise.service.WorkoutService;
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

@Tag(name = "운동 기록", description = "운동 기록 관련 API")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/workout")
public class WorkoutController {

    private final WorkoutService workoutService;

    @Operation(summary = "무산소 운동 기록 저장")
    @PostMapping("/record")
    public CommonResponse<String> recordStrengthEx(
            @AuthenticationPrincipal User user,
            @RequestBody Map<String, Object> workoutMap) {
        return CommonResponse.success(workoutService.recordStrengthEx(user,workoutMap));
    }



}
