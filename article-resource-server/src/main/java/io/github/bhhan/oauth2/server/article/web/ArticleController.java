package io.github.bhhan.oauth2.server.article.web;

import io.github.bhhan.oauth2.server.article.service.ArticleDto;
import io.github.bhhan.oauth2.server.article.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import java.util.List;

@RestController
@RequestMapping("/v1/articles")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;

    @GetMapping
    public List<ArticleDto.Response> getAllArticle() {
        return articleService.getAllArticle();
    }

    @PostMapping
    public ArticleDto.Response addArticle(@RequestBody ArticleDto.Request request) {
        return articleService.addArticle(request);
    }

    @GetMapping("/{articleId}")
    public ArticleDto.Response getArticleById(@PathVariable Long articleId) {
        return articleService.getArticleById(articleId);
    }

    @DeleteMapping("/{articleId}")
    public void deleteArticleById(@PathVariable Long articleId) {
        articleService.deleteArticleById(articleId);
    }
}

