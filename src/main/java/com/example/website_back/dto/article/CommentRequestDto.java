package com.example.website_back.dto.article;

import lombok.Getter;

@Getter
public class CommentRequestDto {
    private Long articleId;
    private String body;

}
