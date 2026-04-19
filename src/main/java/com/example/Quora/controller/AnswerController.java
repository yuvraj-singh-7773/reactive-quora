package com.example.Quora.controller;

import com.example.Quora.dto.AnswerRequestDTO;
import com.example.Quora.dto.AnswerResponseDTO;
import com.example.Quora.service.IAnswerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/answers")
@RequiredArgsConstructor
public class AnswerController {

    private final IAnswerService answerService;

    @PostMapping()
    public Mono<AnswerResponseDTO> createAnswer(@Valid @RequestBody AnswerRequestDTO answerRequestDTO) {
        return answerService.createAnswer(answerRequestDTO)
                .doOnSuccess(response -> System.out.println("Answer created successfully " + response))
                .doOnError(error -> System.out.println("Error occur while creating the answer " + error));
    }

    @GetMapping("/{id}")
    public Mono<AnswerResponseDTO> getAnswerById(@PathVariable String id) {
        return answerService.getAnswerById(id)
                .doOnSuccess(response -> System.out.println("Answer fetched by id: " + response))
                .doOnError(error -> System.out.println("Error while fetching answer by id: " + error));
    }

    @GetMapping("/question/{questionId}")
    public Flux<AnswerResponseDTO> getAnswersByQuestionId(@PathVariable String questionId) {
        return answerService.getAnswersByQuestionId(questionId)
                .doOnNext(response -> System.out.println("Answer fetched by question id: " + response))
                .doOnError(error -> System.out.println("Error while fetching answers by question id: " + error));
    }

    @DeleteMapping("/{id}")
    public Mono<String> deleteAnswerById(@PathVariable String id) {
        return answerService.deleteAnswerById(id)
                .then(Mono.just("Answer is deleted successfully with id" + id))
                .doOnSuccess(response -> System.out.println("Answer deleted successfully with id: " + id))
                .doOnError(error -> System.out.println("Error while deleting answer with id: " + id + " Error: " + error));
    }
}
