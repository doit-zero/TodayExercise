package com.example.todayexercise.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CardioExRequestDTO {
    List<CardioExDTO> CardioEx;

    @Schema(description = "운동 시간", example = "6000")
    private String workTime;
}
