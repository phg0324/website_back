package com.example.website_back.dto.article;

import com.example.website_back.entity.article.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentResponseDto {
    private long commentId;
    private String memberNickname;
    private String commentBody;
    private String createdAt;
    private boolean isWritten;

    public static CommentResponseDto of(Comment comment, boolean bool) {
        return CommentResponseDto.builder()
                .commentId(comment.getId())
                .memberNickname(comment.getMember().getNickname())
                .commentBody(comment.getText())
                .createdAt(comment.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .isWritten(bool)
                .build();
    }
}
