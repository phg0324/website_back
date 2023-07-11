package com.example.website_back.entity.article;

import com.example.website_back.entity.Member;
import lombok.Getter;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    private Long id;

    @Column(nullable = false)
    private String type;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String body;

    @Column(nullable = false)
    private int numReads = 0;
    @CreationTimestamp
    @Column
    private LocalDateTime createdAt = LocalDateTime.now();
    @UpdateTimestamp
    @Column
    private LocalDateTime updatedAt = LocalDateTime.now();
    @OneToMany(mappedBy = "article", cascade = CascadeType.REMOVE)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "article", cascade = CascadeType.REMOVE)
    private List<Recommend> recommends = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public static Article createArticle (String type, String title, String body, Member member) {
        Article article = new Article();
        article.type= type;
        article.title = title;
        article.body = body;
        article.member = member;
        return article;
    }

    public static Article changeArticle (Article article, String title, String type, String body) {
        article.title = title;
        article.type = type;
        article.body = body;
        return article;
    }
    public void setNumReads(int numReads){
        this.numReads = numReads;
    }
}
