package com.example.Quora.repository;

import com.example.Quora.dto.QuestionResponseDTO;
import com.example.Quora.models.Question;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends ReactiveMongoRepository<Question,String> {


}
