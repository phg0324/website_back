package com.example.website_back.repository.article;

import com.example.website_back.entity.article.Article;
import com.example.website_back.entity.article.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByArticle(Article article);
}