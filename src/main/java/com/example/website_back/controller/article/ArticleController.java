package com.example.website_back.controller.article;

import com.example.website_back.dto.article.*;


import com.example.website_back.service.article.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/article")
public class ArticleController {
    private final ArticleService ArticleService;

    @GetMapping("/page")
    public ResponseEntity<Page<PageResponseDto>> pageArticle(@RequestParam(name = "page")int page,
                                                             @RequestParam(name = "type")String type,
                                                             @RequestParam(name = "sort")String sort){
        return ResponseEntity.ok(ArticleService.pageArticle(page, type, sort));
    }

    @GetMapping("/one")
    public ResponseEntity<ArticleResponseDto> getOneArticle(@RequestParam(name = "id") Long id){
        return ResponseEntity.ok(ArticleService.oneArticle(id));
    }

    @PostMapping("/")
    public ResponseEntity<ArticleResponseDto> createArticle(@RequestBody CreateArticleRequestDto request){
        return ResponseEntity.ok(ArticleService.postArticle(request.getType(), request.getTitle(),request.getBody()));
    }

    @GetMapping("/change")
    public ResponseEntity<ArticleResponseDto> getChangeArticle(@RequestParam(name ="id") Long id){
        return ResponseEntity.ok(ArticleService.oneArticle(id));
    }

    @PutMapping("/")
    public ResponseEntity<ArticleResponseDto> putChangeArticle(@RequestBody ChangeArticleRequestDto request){
    return ResponseEntity.ok(ArticleService.changeArticle(
            request.getId(), request.getTitle(), request.getType(), request.getBody()
    ));
    }
    @DeleteMapping("/one")
    public ResponseEntity<MessageDto> deleteArticle(@RequestParam(name = "id") Long id){
        ArticleService.deleteArticle(id);
        return ResponseEntity.ok(new MessageDto("Success"));
    }
}
