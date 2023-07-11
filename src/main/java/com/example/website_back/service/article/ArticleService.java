package com.example.website_back.service.article;

import com.example.website_back.config.SecurityUtil;
import com.example.website_back.dto.article.ArticleResponseDto;
import com.example.website_back.dto.article.PageResponseDto;
import com.example.website_back.entity.Member;
import com.example.website_back.entity.article.Article;
import com.example.website_back.repository.MemberRepository;
import com.example.website_back.repository.article.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final MemberRepository memberRepository;

    public ArticleResponseDto oneArticle(Long id){
        Article article = articleRepository.findById(id).orElseThrow(() -> new RuntimeException("글이 없음"));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        int currentReads = article.getNumReads();
        article.setNumReads(currentReads+1);
        if (authentication == null || authentication.getPrincipal() == "anonymousUser"){
            return ArticleResponseDto.of(article, false);
        } else{
            Member member = memberRepository.findById(Long.parseLong(authentication.getName())).orElseThrow();
            boolean result = article.getMember().equals(member);
            return ArticleResponseDto.of(article, result);
        }
    }
    public Page<PageResponseDto> pageArticle(int pageNum, String pageType, String sort){
        if (pageType.equals("전체")){
            return articleRepository.searchAll(PageRequest.of(pageNum -1, 20) ,sort);
        }
        else{
            return articleRepository.searchType(PageRequest.of(pageNum -1, 20), pageType, sort);
        }

    }

    @Transactional
    public ArticleResponseDto postArticle(String type, String title, String body){
        Member member = isMemberCurrent();
        Article article = Article.createArticle(type, title, body, member);
        articleRepository.save(article);
        return ArticleResponseDto.of(articleRepository.save(article), true);
    }
    @Transactional
    public ArticleResponseDto changeArticle(Long id, String title, String type, String body){
        Article article = authorizationArticleWriter(id);
        return ArticleResponseDto.of(articleRepository.save(Article.changeArticle(article, title,type, body)), true);
    }

    @Transactional
    public void deleteArticle(Long id){
        Article article = authorizationArticleWriter(id);
        articleRepository.delete(article);
    }
    public Member isMemberCurrent() {
        return memberRepository.findById(SecurityUtil.getCurrentMemberId())
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다"));
    }
    public Article authorizationArticleWriter(Long id) {
        Member member = isMemberCurrent();
        Article article = articleRepository.findById(id).orElseThrow(() -> new RuntimeException("글이 읍서."));
        if (!article.getMember().equals(member)) {
            throw new RuntimeException("로그인한 유저와 작성 유저가 같지 않습니다.");
        }
        return article;
    }

}