package com.example.todayexercise.controller;

import com.example.todayexercise.common.CommonResponse;
import com.example.todayexercise.dto.request.SingUpDTO;
import com.example.todayexercise.entity.User;
import com.example.todayexercise.repository.StrengthEx.StrengthExRepository;
import com.example.todayexercise.service.StrengthExService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Tag(name = "무산소 운동", description = "무산소운동 관련 API")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/strength")
public class StrengthExController {

    private final StrengthExService strengthExService;

    @Operation(summary = "요일별 단건 조회 ")
    @GetMapping("/{localdate}")
    public CommonResponse<?> getStrengthEx(@AuthenticationPrincipal User user, @PathVariable(name = "localdate") String localdate) {
        LocalDate localDate = LocalDate.parse(localdate);
        return CommonResponse.success(strengthExService.getStrengthEx(user, localDate));
    }

    @Operation(summary = "유저 운동 기록 전체 조회")
    @GetMapping()
    public CommonResponse<?> getAllStrengthEx(@AuthenticationPrincipal User user) {
        return CommonResponse.success(strengthExService.getAllStrengthEx(user));
    }

}
