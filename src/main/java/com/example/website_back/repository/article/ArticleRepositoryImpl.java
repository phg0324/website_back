package com.example.website_back.repository.article;

import com.example.website_back.dto.article.PageResponseDto;
import com.example.website_back.entity.article.Article;
import com.example.website_back.entity.article.QArticle;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.website_back.entity.article.QArticle.article;
import static com.example.website_back.entity.article.QComment.comment;
import static com.example.website_back.entity.article.QRecommend.recommend;

@RequiredArgsConstructor
@Repository
public class ArticleRepositoryImpl implements ArticleRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<PageResponseDto> searchAll(Pageable pageable, String sort){
        OrderSpecifier temp = article.id.desc();

        if (sort.equals("view")){
            temp = article.numReads.desc();
        }
        if (sort.equals("likes")){
            temp = article.recommends.size().desc();
        }
        if (sort.equals("comments")){
            temp = article.comments.size().desc();
        }
        List<Article> content = queryFactory
                .selectFrom(article)
                .leftJoin(article.recommends, recommend)
                .leftJoin(article.comments, comment)
                .fetchJoin()
                .orderBy(temp, article.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        List<PageResponseDto> pages = content
                .stream()
                .map(article -> {
                    int recommendCount = article.getRecommends().size();
                    int commentCount = article.getComments().size();
                    return PageResponseDto.of(article, recommendCount, commentCount);
                })
                .collect(Collectors.toList());
        int totalSize = queryFactory
                .selectFrom(article)
                .fetch()
                .size();

        return new PageImpl<>(pages, pageable, totalSize);
    }
    @Override
    public Page<PageResponseDto> searchType(Pageable pageable, String type, String sort){
        OrderSpecifier temp = article.id.desc();
        if (sort.equals("view")){
            temp = article.numReads.desc();
        }
        if (sort.equals("likes")){
            temp = article.recommends.size().desc();
        }
        if (sort.equals("comments")){
            temp = article.comments.size().desc();
        }
        List<Article> content = queryFactory
                .selectFrom(article)
                .leftJoin(article.recommends, recommend)
                .leftJoin(article.comments, comment)
                .fetchJoin()
                .where(article.type.eq(type))
                .orderBy(temp, article.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        List<PageResponseDto> pages = content
                .stream()
                .map(article -> {
                    int recommendCount = article.getRecommends().size();
                    int commentCount = article.getComments().size();
                    return PageResponseDto.of(article, recommendCount,commentCount);
                })
                .collect(Collectors.toList());
        int totalSize = queryFactory
                .selectFrom(article)
                .where(article.type.eq(type))
                .fetch()
                .size();
        return new PageImpl<>(pages, pageable, totalSize);
    }
}
