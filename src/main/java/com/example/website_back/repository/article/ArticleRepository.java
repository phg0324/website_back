package com.example.website_back.repository.article;


import com.example.website_back.entity.article.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository <Article, Long>, ArticleRepositoryCustom {

}
