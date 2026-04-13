package com.example.Quora.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaginationDTO {
    private long total_records;

    private int current_page;

    private int total_page;

    private Integer next_page;

    private Integer prev_page;
}
