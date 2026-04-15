package com.example.Quora.models;


import com.example.Quora.enums.TargetType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "likes")
public class Like extends BaseEntity {

    private String targetId;

    private TargetType targetType;

    private Boolean isLike;


}
