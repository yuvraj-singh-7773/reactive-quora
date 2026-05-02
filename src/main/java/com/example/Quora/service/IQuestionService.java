package com.example.Quora.service;


import com.example.Quora.dto.QuestionPaginationResponseDTO;
import com.example.Quora.dto.QuestionRequestDTO;
import com.example.Quora.dto.QuestionResponseDTO;
import com.example.Quora.models.Question;
import com.example.Quora.models.QuestionElasticDocument;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface IQuestionService {

    public Mono<QuestionResponseDTO> createQuestion(QuestionRequestDTO questionRequestDTO);

    public Mono<QuestionResponseDTO> getQuestionById(String id);

    public Mono<QuestionPaginationResponseDTO> getAllQuestions(String cursor, int size);

    public Mono<QuestionPaginationResponseDTO> SearchQuestions(String query, int page, int size);

    public Mono<QuestionPaginationResponseDTO> getQuestionsByTag(String tag, int page, int size);

    public Mono<Void> deleteQuestionById(String id);

    public List<QuestionElasticDocument> searchQuestionsByElasticsearch(String query);

}
