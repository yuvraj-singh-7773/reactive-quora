package com.example.Quora.repository;


import com.example.Quora.dto.QuestionRequestDTO;
import com.example.Quora.dto.QuestionResponseDTO;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface AnswerService {
    public Mono<QuestionResponseDTO> createQuestion(QuestionRequestDTO questionRequestDTO);

    public Mono<QuestionResponseDTO> getQuestionById(String id);

}
