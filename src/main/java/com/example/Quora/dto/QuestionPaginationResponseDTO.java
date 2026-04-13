package com.example.Quora.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionPaginationResponseDTO {

    private List<QuestionResponseDTO> questionsResponseDTO;

    private PaginationDTO paginationDTO;
}
