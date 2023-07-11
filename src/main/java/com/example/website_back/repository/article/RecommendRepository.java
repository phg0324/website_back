package com.example.website_back.repository.article;

import com.example.website_back.entity.article.Article;
import com.example.website_back.entity.article.Recommend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecommendRepository extends JpaRepository<Recommend, Long> {
    List<Recommend> findAllByArticle(Article article);
}
