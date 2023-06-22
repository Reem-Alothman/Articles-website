package com.example.demo.mapstruct.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.Size;

@Getter
@Setter
public class ArticleDto {

    @Size(max = 100, message = "title should be 100 characters or less")
    private String title;

    @Size(max = 500, message = "body should be 500 characters or less")
    private String body;


    public void ArticleDto(String title, String body){
        this.title = title;
        this.body = body;
    }
}

