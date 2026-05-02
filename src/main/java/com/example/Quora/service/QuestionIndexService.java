package com.example.Quora.service;

import com.example.Quora.mapper.QuestionElasticMapper;
import com.example.Quora.models.Question;
import com.example.Quora.models.QuestionElasticDocument;
import com.example.Quora.repository.QuestionDocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionIndexService implements IQuestionIndexService {

    private final QuestionDocumentRepository questionDocumentRepository;

    public void createQuestionIndex(Question question) {
        QuestionElasticDocument document = QuestionElasticMapper.toElasticDocument(question);

        questionDocumentRepository.save(document);

    }
}
