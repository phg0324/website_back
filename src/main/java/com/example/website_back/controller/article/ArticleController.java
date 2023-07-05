package com.example.website_back.controller.article;

import com.example.website_back.dto.article.*;
import com.example.website_back.service.article.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/entryArticle")
public class EntryArticleController {
    private final ArticleService articleService;

    @GetMapping("/page")
    public ResponseEntity<Page<EntryPageResponseDto>> pageEntryArticle(@RequestParam(name = "page") int page) {
        return ResponseEntity.ok(articleService.pageEntryArticle(page));
    }

    @GetMapping("/one")
    public ResponseEntity<EntryArticleResponseDto> getOneEntryArticle(@RequestParam(name = "id") Long id){
        return ResponseEntity.ok(articleService.oneEntryArticle(id));
    }

    @PostMapping("/")
    public ResponseEntity<EntryArticleResponseDto> createEntryArticle(@RequestBody CreateArticleRequestDto request){
        return ResponseEntity.ok(articleService.postEntryArticle(request.getTitle(),request.getBody()));
    }

    @GetMapping("/change")
    public ResponseEntity<EntryArticleResponseDto> getChangeEntryArticle(@RequestParam(name ="id") Long id){
        return ResponseEntity.ok(articleService.oneEntryArticle(id));
    }

    @PutMapping("/")
    public ResponseEntity<EntryArticleResponseDto> putChangeEntryArticle(@RequestBody ChangeArticleRequestDto request){
    return ResponseEntity.ok(articleService.changeEntryArticle(
            request.getId(), request.getTitle(), request.getBody()
    ));
    }
    @DeleteMapping("/one")
    public ResponseEntity<MessageDto> deleteEntryArticle(@RequestParam(name = "id") Long id){
        articleService.deleteArticle(id);
        return ResponseEntity.ok(new MessageDto("Success"));
    }
}
