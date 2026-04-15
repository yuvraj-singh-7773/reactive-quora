package com.example.Quora.events;

import com.example.Quora.enums.TargetType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ViewCountEvent {

    private String targetId;
    private TargetType targetType;
    private LocalDateTime timestamp;
}
