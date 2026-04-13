package com.example.Quora.utils;

import com.example.Quora.dto.PaginationDTO;

public class PaginationUtils {
    public static PaginationDTO buildPagination(long totalRecords, int currentPage, int pageSize) {
        int totalPages = (int) Math.ceil((double) totalRecords / pageSize);

        Integer nextPage = currentPage < totalPages - 1 ? currentPage + 1 : null;

        Integer prevPage = currentPage > 0 ? currentPage - 1 : null;

        return PaginationDTO.builder()
                .total_records(totalRecords)
                .current_page(currentPage)
                .total_page(totalPages)
                .next_page(nextPage)
                .prev_page(prevPage)
                .build();
    }
}
