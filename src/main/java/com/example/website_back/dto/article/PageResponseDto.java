package com.example.website_back.dto.article;

import com.example.website_back.entity.article.Article;
import lombok.Builder;
import lombok.Getter;

import java.time.format.DateTimeFormatter;

@Getter
@Builder
public class PageResponseDto {
    private Long articleId;
    private String articleType;
    private String articleTitle;
    private String memberNickname;
    private String createdAt;
    private int articleLikes;
    private int articleComments;
    private int articleReads;


    public static PageResponseDto of(Article article, int likes, int comments) {
        return PageResponseDto.builder()
                .articleId(article.getId())
                .articleType(article.getType())
                .articleTitle(article.getTitle())
                .memberNickname(article.getMember().getNickname())
                .createdAt(article.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .articleLikes(likes)
                .articleComments(comments)
                .articleReads(article.getNumReads())
                .build();
    }
}
