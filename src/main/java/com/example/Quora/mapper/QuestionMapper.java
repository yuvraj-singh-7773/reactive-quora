package com.example.Quora.mapper;

import com.example.Quora.dto.QuestionRequestDTO;
import com.example.Quora.dto.QuestionResponseDTO;
import com.example.Quora.models.Question;

public class QuestionMapper {

    public static Question toEntity(QuestionRequestDTO questionRequestDTO){
        return Question.builder()
                .title(questionRequestDTO.getTitle())
                .content(questionRequestDTO.getContent())
                .build();
    }

    public static QuestionResponseDTO toQuestionResponseDto(Question question) {
        return QuestionResponseDTO.builder()
                .id(question.getId())
                .title(question.getTitle())
                .content(question.getContent())
                .createdAt(question.getCreatedAt())
                .build();
    }


}
