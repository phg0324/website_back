package com.example.website_back.repository.article;

import com.example.website_back.dto.article.EntryPageResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;



public interface EntryArticleRepositoryCustom {
    Page<EntryPageResponseDto> searchAll(Pageable pageable);
}
