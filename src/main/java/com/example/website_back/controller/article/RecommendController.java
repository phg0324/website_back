package com.example.website_back.controller.article;

import com.example.website_back.dto.article.MessageDto;
import com.example.website_back.dto.article.PostRecommendDto;
import com.example.website_back.dto.article.RecommendDto;
import com.example.website_back.service.article.RecommendService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/recommend")
public class RecommendController {
    private final RecommendService recommendService;

    @GetMapping("/list")
    public ResponseEntity<RecommendDto> getRecommends(@RequestParam(name = "id") Long id) {
        return ResponseEntity.ok(recommendService.allRecommend(id));
    }

    @PostMapping("/")
    public ResponseEntity<MessageDto> postRecommend(@RequestBody PostRecommendDto dto) {
        recommendService.createRecommend(dto.getArticleId());
        return ResponseEntity.ok(new MessageDto("Success"));
    }

    @DeleteMapping("/one")
    public ResponseEntity<MessageDto> deleteRecommend(@RequestParam(name = "id") Long id) {
        recommendService.removeRecommend(id);
        return ResponseEntity.ok(new MessageDto("Success"));
    }

}