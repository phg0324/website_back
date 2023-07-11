package com.example.website_back.repository.article;

import com.example.website_back.dto.article.PageResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;



public interface ArticleRepositoryCustom {
    Page<PageResponseDto> searchAll(Pageable pageable, String sort);
    Page<PageResponseDto> searchType(Pageable pageable, String type, String sort);

}
