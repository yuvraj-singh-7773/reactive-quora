package com.example.Quora.service;

import com.example.Quora.dto.QuestionRequestDTO;
import com.example.Quora.dto.QuestionResponseDTO;
import com.example.Quora.mapper.QuestionMapper;
import com.example.Quora.models.Question;
import com.example.Quora.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class QuestionService implements IQuestionService{

    private final QuestionRepository questionRepository;

    @Override
    public Mono<QuestionResponseDTO> createQuestion(QuestionRequestDTO questionRequestDTO) {
        Question question= QuestionMapper.toEntity(questionRequestDTO);
        return questionRepository.save(question)
                .map(QuestionMapper::toQuestionResponseDto)
                .doOnSuccess(response-> System.out.println("Question Created successfully "+response))
                .doOnError(error-> System.out.println("Error Occur During Question Creation"+error));

    }

    @Override
    public Mono<QuestionResponseDTO> getQuestionById(String id) {
        return questionRepository.findById(id)
                .map(QuestionMapper::toQuestionResponseDto)
                .doOnSuccess(response-> System.out.println("Question fetched successfully" +response))
                .doOnError(error-> System.out.println("Error Occur During Question fetching"+error));
    }



}
