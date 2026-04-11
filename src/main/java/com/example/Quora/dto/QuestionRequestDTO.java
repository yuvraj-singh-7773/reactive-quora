package com.example.Quora.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionRequestDTO {

    @NotBlank(message="tittle is required")
    @Size(min=10,max=100,message="tittle must be length between 10 to 100")
    private String title;

    @NotBlank(message = "Context should not be blank")
    @Size(min=10,max=1000,message = "context message must be length between 10 to 1000")
    private String content;

}
