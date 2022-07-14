package io.github.bhhan.oauth2.server.gateway.service;

import io.github.bhhan.oauth2.server.gateway.service.dto.CommentResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentClient {
    private final WebClient webClient;

    public CommentClient(@Value("${comment.service}") String baseUrl, WebClient webClient) {
        this.webClient = webClient
                .mutate()
                .baseUrl(baseUrl + "/v1/comments")
                .build();
    }

    public Mono<List<CommentResponse>> getComments(Long articleId) {
        return Mono.deferContextual(ctx -> Mono.just(ctx.get("Authorization").toString())
                .flatMap(authToken -> this.webClient
                        .get()
                        .uri("/{articleId}", articleId)
                        .header("Authorization", authToken)
                        .retrieve()
                        .bodyToFlux(CommentResponse.class)
                        .onErrorResume(throwable -> Flux.empty())
                        .collect(Collectors.toList())));
    }
}
