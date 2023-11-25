package com.example.todayexercise.controller;

import com.example.todayexercise.common.CommonResponse;
import com.example.todayexercise.dto.request.CardioExRequestDTO;
import com.example.todayexercise.dto.request.StrengthExRequestDTO;
import com.example.todayexercise.entity.User;
import com.example.todayexercise.service.WorkoutService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@Tag(name = "운동 기록", description = "운동 기록 관련 API")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/workout")
public class WorkoutController {

    private final WorkoutService workoutService;

    @Operation(summary = "무산소 운동 기록 저장")
    @PostMapping("/strengthEx")
    public CommonResponse<String> recordStrengthEx(
            @AuthenticationPrincipal User user,
            @RequestBody StrengthExRequestDTO workoutMap) {
        return CommonResponse.success(workoutService.recordStrengthEx(user,workoutMap));
    }

    @Operation(summary = "유산소 운동 기록 저장")
    @PostMapping("/cardioEx")
    public CommonResponse<String> recordCardioEx(
            @AuthenticationPrincipal User user,
            @RequestBody CardioExRequestDTO workoutMap) {
        return CommonResponse.success(workoutService.recordCardioEx(user,workoutMap));
    }

    @Operation(summary = "오늘 날짜 기준으로 일주일 전 요일별 운동 시간 기록 가져오기")
    @GetMapping
    public CommonResponse<List<Map<String,Object>>> getWorkoutList(
            @AuthenticationPrincipal User user) {
        return CommonResponse.success(workoutService.getWorkoutList(user));
    }

    @Operation(summary = "모든 운동 목록 가져오기")
    @GetMapping("/all")
    public CommonResponse<?> getAllWorkoutList(
            @AuthenticationPrincipal User user,
            @RequestParam(value = "cursor", required = false,defaultValue = "0") Long cursor,
            @RequestParam(value = "pageSize", defaultValue = "7") int pageSize) {
        return CommonResponse.success(workoutService.getAllWorkoutList(user,cursor,pageSize));
    }

}
