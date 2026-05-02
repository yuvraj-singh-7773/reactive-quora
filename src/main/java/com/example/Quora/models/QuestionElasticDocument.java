package com.example.Quora.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(indexName = "questions",createIndex = false)
public class QuestionElasticDocument {
    @Id
    private String id;

    private String title;

    private String content;

}
