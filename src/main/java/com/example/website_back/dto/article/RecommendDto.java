package com.example.website_back.dto.article;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class RecommandDto {
    private int recommandNum;
    private boolean isRecommanded;

    public static RecommandDto noOne(){
        return RecommandDto.builder()
                .recommendNum(0)
                .isRecommended(false)
                .build();
    }
}
