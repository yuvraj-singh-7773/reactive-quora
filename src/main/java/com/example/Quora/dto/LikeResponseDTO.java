package com.example.Quora.dto;

import com.example.Quora.enums.TargetType;
import com.example.Quora.models.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LikeResponseDTO{

    private String id;

    private String targetId;

    private TargetType targetType;

    private Boolean isLike;

    private LocalDateTime createdAt;

}
