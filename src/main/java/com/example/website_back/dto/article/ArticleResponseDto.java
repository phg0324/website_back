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

public class EntryArticleResponseDto {
    private Long articleId;
    private Long mainId;
    private String memberNickname;
    private String articleTitle;
    private String articleBody;
    private String createdAt;
    private String updatedAt;
    private boolean isWritten;

    public static EntryArticleResponseDto of(Article article, boolean bool) {
        return EntryArticleResponseDto.builder()
                .articleId(article.getId())
                .mainId(article.getMainId())
                .memberNickname(article.getMember().getNickname())
                .articleTitle(article.getTitle())
                .articleBody(article.getBody())
                .createdAt(article.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .updatedAt(article.getUpdatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .isWritten(bool)
                .build();
    }
}
