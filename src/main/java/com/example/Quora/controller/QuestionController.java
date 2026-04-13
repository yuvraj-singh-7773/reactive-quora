package com.example.Quora.controller;


import com.example.Quora.dto.QuestionPaginationResponseDTO;
import com.example.Quora.dto.QuestionRequestDTO;
import com.example.Quora.dto.QuestionResponseDTO;
import com.example.Quora.service.IQuestionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/questions")
@RequiredArgsConstructor
public class QuestionController {
    private final IQuestionService questionService;

    @PostMapping()
    public Mono<QuestionResponseDTO> createQuestion(@Valid @RequestBody QuestionRequestDTO questionRequestDTO){
        return questionService.createQuestion(questionRequestDTO)
                .doOnSuccess(response-> System.out.println("Question created successfully " + response))
                .doOnError(error->System.out.println("Error occur while creating the question "+error));
    }

    @GetMapping("/{id}")
    public Mono<QuestionResponseDTO> getQuestionById(@PathVariable String id) {
        return questionService.getQuestionById(id)
                .doOnSuccess(response-> System.out.println("Question fetched by id: "+ response))
                .doOnError(error-> System.out.println("Error while fetching question by id: "+ error));
    }

    @GetMapping()
    public Mono<QuestionPaginationResponseDTO> getAllQuestions(String cursor, int size) {
        return questionService.getAllQuestions(cursor,size)
                .doOnNext(response-> System.out.println("Question fetched: "+ response))
                .doOnError(error-> System.out.println("Error while fetching all questions: "+ error));
    }

    @DeleteMapping("/{id}")
    public Mono<String> deleteQuestionById(@PathVariable String id) {
        return questionService.deleteQuestionById(id)
                .then(Mono.just("Question is deleted successfully with id"+id))
                .doOnSuccess(response-> System.out.println("Question deleted successfully with id: "+ id))
                .doOnError(error-> System.out.println("Error while deleting question with id: "+ id + " Error: " + error));

    }

    @GetMapping("/search")
    public Mono<QuestionPaginationResponseDTO> searchQuestions(
            @RequestParam String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
            return questionService.SearchQuestions(query, page, size)
                    .doOnNext(response-> System.out.println("Question fetched by search query: "+ response))
                    .doOnError(error-> System.out.println("Error while searching questions with query: "+ query + " Error: " + error));
    }

    @GetMapping("/tag/{tag}")
    public Mono<QuestionPaginationResponseDTO> getQuestionsByTag(@PathVariable String tag,
                                                       @RequestParam(defaultValue = "0") int page,
                                                       @RequestParam(defaultValue = "10") int size
    ) {
        return questionService.getQuestionsByTag(tag, page, size)
                .doOnNext(response-> System.out.println("Question fetched by tag: "+ response))
                .doOnError(error-> System.out.println("Error while fetching questions with tag: "+ tag + " Error: " + error));
    }


}
