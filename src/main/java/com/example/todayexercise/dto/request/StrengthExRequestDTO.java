package com.example.todayexercise.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class StrengthExRequestDTO {

    private List<StrengthExDTO> StrengthEx;

    @Schema(description = "운동 시간", example = "6000")
    private String workTime;
}
