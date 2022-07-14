package io.github.bhhan.oauth2.server.article.service;

import io.github.bhhan.oauth2.server.article.domain.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    @Transactional(readOnly = true)
    public ArticleDto.Response getArticleById(Long articleId) {
        return new ArticleDto.Response(articleRepository.findById(articleId)
                .orElseThrow(IllegalArgumentException::new));
    }

    @Transactional(readOnly = true)
    public List<ArticleDto.Response> getAllArticle() {
        return articleRepository.findAll()
                .stream().map(ArticleDto.Response::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public ArticleDto.Response addArticle(ArticleDto.Request request){
        return new ArticleDto.Response(articleRepository.save(request.toEntity()));
    }

    @Transactional
    public void deleteArticleById(Long articleId) {
        articleRepository.deleteById(articleId);
    }
}
