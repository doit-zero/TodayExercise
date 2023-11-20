package com.example.todayexercise.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StrengthExDTO {

    @Schema(description = "운동 부위", example = "가슴/등/어깨/팔/하체")
    private String part;

    @Schema(description = "운동 이름", example = "벤치프레스")
    private String exName;

    @Schema(description = "무게", example = "10")
    private int kg;

    @Schema(description = "반복 횟수 ", example = "4")
    private int rep;

    @Schema(description = "새트", example = "5")
    private int set;
}
