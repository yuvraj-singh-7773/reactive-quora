package com.example.Quora.service;

import com.example.Quora.dto.AnswerRequestDTO;
import com.example.Quora.dto.AnswerResponseDTO;
import com.example.Quora.mapper.AnswerMapper;
import com.example.Quora.models.Answer;
import com.example.Quora.repository.AnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AnswerService implements IAnswerService {

    private final AnswerRepository answerRepository;

    @Override
    public Mono<AnswerResponseDTO> createAnswer(AnswerRequestDTO answerRequestDTO) {
        Answer answer = AnswerMapper.toEntity(answerRequestDTO);
        return answerRepository.save(answer)
                .map(AnswerMapper::toAnswerResponseDTO)
                .doOnSuccess(response -> System.out.println("Answer Created successfully " + response))
                .doOnError(error -> System.out.println("Error Occur During Answer Creation" + error));
    }

    @Override
    public Mono<AnswerResponseDTO> getAnswerById(String id) {
        return answerRepository.findById(id)
                .map(AnswerMapper::toAnswerResponseDTO)
                .doOnSuccess(response -> System.out.println("Answer fetched successfully: " + response))
                .doOnError(error -> System.out.println("Error Occur During Answer fetching" + error));
    }

    @Override
    public Flux<AnswerResponseDTO> getAnswersByQuestionId(String questionId) {
        return answerRepository.findByQuestionId(questionId)
                .map(AnswerMapper::toAnswerResponseDTO)
                .doOnNext(response -> System.out.println("Answers fetched successfully by question id: " + response))
                .doOnError(error -> System.out.println("Error Occur During Answers fetching by question id" + error));
    }

    @Override
    public Mono<Void> deleteAnswerById(String id) {
        return answerRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Not found")))
                .flatMap(answer -> answerRepository.delete(answer));
    }
}
