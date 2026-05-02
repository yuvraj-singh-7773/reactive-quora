package com.example.Quora.service;

import com.example.Quora.dto.PaginationDTO;
import com.example.Quora.dto.QuestionPaginationResponseDTO;
import com.example.Quora.dto.QuestionRequestDTO;
import com.example.Quora.dto.QuestionResponseDTO;
import com.example.Quora.enums.TargetType;
import com.example.Quora.events.ViewCountEvent;
import com.example.Quora.mapper.QuestionMapper;
import com.example.Quora.models.Question;
import com.example.Quora.models.QuestionElasticDocument;
import com.example.Quora.producer.KafkaEventProducer;
import com.example.Quora.repository.QuestionDocumentRepository;
import com.example.Quora.repository.QuestionRepository;
import com.example.Quora.utils.CursorUtils;
import com.example.Quora.utils.PaginationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService implements IQuestionService{

    private final QuestionRepository questionRepository;

    private final KafkaEventProducer kafkaEventProducer;

    private final QuestionDocumentRepository questionDocumentRepository;

    private final IQuestionIndexService questionIndexService;

    @Override
    public Mono<QuestionResponseDTO> createQuestion(QuestionRequestDTO questionRequestDTO) {
        Question question= QuestionMapper.toEntity(questionRequestDTO);
        return questionRepository.save(question)
               .flatMap(savedQuestion -> {
                   questionIndexService.createQuestionIndex(savedQuestion);
                   return Mono.just(savedQuestion);
               })
                .map(savedQuestion ->
                        QuestionMapper.toQuestionResponseDto(savedQuestion)
                )
                .doOnSuccess(response-> System.out.println("Question Created successfully "+response))
                .doOnError(error-> System.out.println("Error Occur During Question Creation"+error));

    }

    @Override
    public Mono<QuestionResponseDTO> getQuestionById(String id) {
        return questionRepository.findById(id)
                .map(QuestionMapper::toQuestionResponseDto)
                .doOnSuccess(response -> {
                    System.out.println("Question fetched successfully: " + response);

                    ViewCountEvent viewCountEvent = new ViewCountEvent(
                            id,
                            TargetType.Questions,
                            LocalDateTime.now()
                    );

                    kafkaEventProducer.publishViewCountEvent(viewCountEvent);
                })
                .doOnError(error-> System.out.println("Error Occur During Question fetching"+error));
    }

    @Override
    public Mono<QuestionPaginationResponseDTO> getAllQuestions(String cursor, int size) {
        Pageable pageable= PageRequest.of(0,size);
        if(!CursorUtils.isValidCursor(cursor)){
            return questionRepository.findTop10ByOrderByCreatedAtAsc()
                    .take(size)
                    .map(QuestionMapper::toQuestionResponseDto)
                    .collectList()
                    .map(questionList -> {

                        PaginationDTO paginationDTO =
                                PaginationUtils.buildPagination(
                                        questionList.size(), 0, size
                                );

                        return QuestionMapper.toQuestionPaginationResponseDTO(
                                questionList,
                                paginationDTO
                        );
                    });
        }
        else{
            LocalDateTime cursorTimeStamp = CursorUtils.parseCursor(cursor);
            return questionRepository.count()
                    .flatMap(
                            totalCount->{
                                PaginationDTO paginationDTO=PaginationUtils.buildPagination(totalCount,0,size);
                                return questionRepository.findByCreatedAtGreaterThanOrderByCreatedAtAsc(cursorTimeStamp,pageable)
                                        .map(QuestionMapper::toQuestionResponseDto)
                                        .collectList()
                                        .map(questionList->
                                                QuestionMapper.toQuestionPaginationResponseDTO(questionList,paginationDTO)
                                        );
                            }

                    );
        }
    }

    @Override
    public Mono<QuestionPaginationResponseDTO> SearchQuestions(String query, int page, int size) {
        Pageable pageable=PageRequest.of(page,size);
        return questionRepository.countByTitleOrContentContainingIgnoreCase(query,query)
                .flatMap(
                        totalCount->{
                            PaginationDTO paginationDTO=PaginationUtils.buildPagination(totalCount,page,size);
                            return questionRepository
                                    .findByTitleOrContentContainingIgnoreCase(query,pageable)
                                    .map(QuestionMapper::toQuestionResponseDto)
                                    .collectList()
                                    .map(questionList->
                                            QuestionMapper.toQuestionPaginationResponseDTO(questionList,paginationDTO));
                        }
                );
    }

    @Override
    public Mono<QuestionPaginationResponseDTO> getQuestionsByTag(String tag, int page, int size) {
        Pageable pageable=PageRequest.of(page,size);
        return questionRepository.countByTag(tag)
                .flatMap(
                        totalCount-> {
                            PaginationDTO paginationDTO=PaginationUtils.buildPagination(totalCount,page,size);
                            return questionRepository.findByTag(tag,pageable)
                                    .map(QuestionMapper::toQuestionResponseDto)
                                    .collectList()
                                    .map(questionList ->
                                            QuestionMapper.toQuestionPaginationResponseDTO(
                                                    questionList, paginationDTO
                                            )
                                    );

                        }

                );
    }

    @Override
    public Mono<Void> deleteQuestionById(String id) {
        return questionRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Not found")))
                .flatMap(q -> questionRepository.delete(q));
    }

    @Override
    public List<QuestionElasticDocument> searchQuestionsByElasticsearch(String query) {
        return questionDocumentRepository.findByTitleContainingOrContentContaining(query, query);
    }

}
