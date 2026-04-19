package com.example.Quora.mapper;

import com.example.Quora.dto.AnswerRequestDTO;
import com.example.Quora.dto.AnswerResponseDTO;
import com.example.Quora.models.Answer;

public class AnswerMapper {

    public static Answer toEntity(AnswerRequestDTO answerRequestDTO) {
        return Answer.builder()
                .content(answerRequestDTO.getContent())
                .questionId(answerRequestDTO.getQuestionId())
                .build();
    }

    public static AnswerResponseDTO toAnswerResponseDTO(Answer answer) {
        return AnswerResponseDTO.builder()
                .id(answer.getId())
                .content(answer.getContent())
                .questionId(answer.getQuestionId())
                .createdAt(answer.getCreatedAt())
                .updatedAt(answer.getUpdatedAt())
                .build();
    }
}
