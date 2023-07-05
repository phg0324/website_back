package com.example.website_back.repository.article;

import com.example.website_back.entity.article.Recommand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecommandRepository extends JpaRepository<Recommand, Long> {

}
