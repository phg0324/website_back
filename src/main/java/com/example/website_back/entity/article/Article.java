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
public class EntryArticle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "entry_article_id")
    private Long id;

    @Column(nullable = false)
    private Long mainId;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String body;

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

    public static EntryArticle createArticle (Long mainId, String title, String body, Member member) {
        EntryArticle enarticle = new EntryArticle();
        enarticle.mainId = mainId;
        enarticle.title = title;
        enarticle.body = body;
        enarticle.member = member;

        return enarticle;
    }

    public static EntryArticle changeArticle (EntryArticle enarticle, String title, String body) {
        enarticle.title = title;
        enarticle.body = body;

        return enarticle;
    }
}
