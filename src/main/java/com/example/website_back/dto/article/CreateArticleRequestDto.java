package com.example.website_back.dto.article;

import lombok.Getter;

@Getter
public class CreateArticleRequestDto {
    private String type;
    private String title;
    private String body;
}
