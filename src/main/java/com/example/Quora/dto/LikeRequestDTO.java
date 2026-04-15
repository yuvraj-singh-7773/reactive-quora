package com.example.Quora.dto;

import com.example.Quora.enums.TargetType;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.annotation.Target;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LikeRequestDTO {

    @NotBlank(message = "Target Id is Required")
    private String targetId;

    @NotBlank(message = "Target Type is Required")
    private TargetType targetType;

    @NotBlank (message = "isLike is Required")
    private Boolean isLike;
}
