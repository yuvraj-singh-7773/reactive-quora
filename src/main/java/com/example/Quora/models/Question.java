package com.example.Quora.models;


import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection="questions")
public class Question extends BaseEntity{

    private String title;

    private String content;

    private String tag;
}
