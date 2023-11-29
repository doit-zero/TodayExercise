package com.example.todayexercise.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;


@Getter
public class CardioExDTO {

    @Schema(description = "운동 이름", example = "러닝")
    private String exName;

    @Schema(description = "뛴 거리",example = "6")
    private Integer km;

}
