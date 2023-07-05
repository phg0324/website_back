package com.example.website_back.repository.article;

import com.example.website_back.dto.article.EntryPageResponseDto;
import com.example.website_back.entity.article.Article;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.website_back.entity.article.QEntryArticle.entryArticle;
@RequiredArgsConstructor
@Repository
public class EntryArticleRepositoryImpl implements EntryArticleRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<EntryPageResponseDto> searchAll(Pageable pageable){
        List<Article> content = queryFactory
                .selectFrom(entryArticle)
                .orderBy(entryArticle.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        List<EntryPageResponseDto> pages = content
                .stream()
                .map(EntryPageResponseDto::of)
                .collect(Collectors.toList());
        int totalSize = queryFactory
                .selectFrom(entryArticle)
                .fetch()
                .size();
        return new PageImpl<>(pages, pageable, totalSize);
    }
}
