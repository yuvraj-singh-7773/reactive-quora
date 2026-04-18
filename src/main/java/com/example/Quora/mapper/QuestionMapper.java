package com.example.Quora.mapper;

import com.example.Quora.dto.PaginationDTO;
import com.example.Quora.dto.QuestionPaginationResponseDTO;
import com.example.Quora.dto.QuestionRequestDTO;
import com.example.Quora.dto.QuestionResponseDTO;
import com.example.Quora.models.Question;
import com.example.Quora.utils.PaginationUtils;

import java.util.List;


public class QuestionMapper {

    public static Question toEntity(QuestionRequestDTO questionRequestDTO){
        return Question.builder()
                .title(questionRequestDTO.getTitle())
                .content(questionRequestDTO.getContent())
                .tag(questionRequestDTO.getTag())
                .build();
    }

    public static QuestionResponseDTO toQuestionResponseDto(Question question) {
        return QuestionResponseDTO.builder()
                .id(question.getId())
                .title(question.getTitle())
                .content(question.getContent())
                .views(question.getViews())
                .createdAt(question.getCreatedAt())
                .build();
    }

    public static QuestionPaginationResponseDTO toQuestionPaginationResponseDTO(List<QuestionResponseDTO> question, PaginationDTO paginationDTO){


        return QuestionPaginationResponseDTO.builder()
                .questionsResponseDTO(question)
                .paginationDTO(paginationDTO)
                .build();
    }

}
