package com.example.website_back.dto.article;

import com.example.website_back.entity.article.Article;
import lombok.Builder;
import lombok.Getter;

import java.time.format.DateTimeFormatter;

@Getter
@Builder
public class EntryPageResponseDto {
    private Long articleId;
    private String articleTitle;
    private String memberNickname;
    private String createdAt;


    public static EntryPageResponseDto of(Article article) {
        return EntryPageResponseDto.builder()
                .articleId(article.getId())
                .articleTitle(article.getTitle())
                .memberNickname(article.getMember().getNickname())
                .createdAt(article.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .build();
    }
}
