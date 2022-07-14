package io.github.bhhan.oauth2.server.gateway.service;

import io.github.bhhan.oauth2.server.gateway.service.dto.ArticleResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ArticleClient {
    private final WebClient webClient;

    public ArticleClient(@Value("${article.service}") String baseUrl, WebClient webClient) {
        this.webClient = webClient
                .mutate()
                .baseUrl(baseUrl + "/v1/articles")
                .build();
    }

    public Mono<ArticleResponse> getArticle(Long articleId) {
        return Mono.deferContextual(ctx -> Mono.just(ctx.get("Authorization").toString())
                .flatMap(authToken -> this.webClient
                        .get()
                        .uri("/{articleId}", articleId)
                        .header("Authorization", authToken)
                        .retrieve()
                        .bodyToMono(ArticleResponse.class)
                        .onErrorResume(throwable -> Mono.empty())));
    }

}
