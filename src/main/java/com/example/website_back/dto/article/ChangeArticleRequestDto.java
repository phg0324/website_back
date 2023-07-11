package com.example.website_back.dto.article;

import lombok.Getter;

@Getter
public class ChangeArticleRequestDto {
    private Long id;
    private String title;
    private String type;
    private String body;
}
