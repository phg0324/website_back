package com.example.website_back.dto.article;

import com.example.website_back.entity.article.Article;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ArticleResponseDto {
    private Long articleId;
    private String articleType;
    private String memberNickname;
    private String articleTitle;
    private String articleBody;
    private String createdAt;
    private String updatedAt;
    private int articleReads;
    private boolean isWritten;

    public static ArticleResponseDto of(Article article, boolean bool) {
        return ArticleResponseDto.builder()
                .articleId(article.getId())
                .articleType(article.getType())
                .memberNickname(article.getMember().getNickname())
                .articleTitle(article.getTitle())
                .articleBody(article.getBody())
                .createdAt(article.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .updatedAt(article.getUpdatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .articleReads(article.getNumReads())
                .isWritten(bool)
                .build();
    }
}
