package com.example.Quora.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "answers")
public class Answer extends BaseEntity {

    @NotBlank(message = "Content is required")
    @Size(min = 10, max = 1000, message = "Content must be between 10 and 1000 characters")
    private String content;

    @Indexed
    private String questionId;
}
