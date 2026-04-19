package com.example.Quora.service;

import com.example.Quora.dto.AnswerRequestDTO;
import com.example.Quora.dto.AnswerResponseDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IAnswerService {

    public Mono<AnswerResponseDTO> createAnswer(AnswerRequestDTO answerRequestDTO);

    public Mono<AnswerResponseDTO> getAnswerById(String id);

    public Flux<AnswerResponseDTO> getAnswersByQuestionId(String questionId);

    public Mono<Void> deleteAnswerById(String id);
}
