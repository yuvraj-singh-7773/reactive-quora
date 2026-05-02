package com.example.Quora.mapper;

import com.example.Quora.models.Question;
import com.example.Quora.models.QuestionElasticDocument;

public class QuestionElasticMapper {
    public static QuestionElasticDocument toElasticDocument(Question question) {
        return QuestionElasticDocument.builder()
                .id(question.getId())
                .title(question.getTitle())
                .content(question.getContent())
                .build();
    }
}
