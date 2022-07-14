package io.github.bhhan.oauth2.server.gateway.service;

import io.github.bhhan.oauth2.server.gateway.service.dto.AggregateResponse;
import io.github.bhhan.oauth2.server.gateway.service.dto.ArticleResponse;
import io.github.bhhan.oauth2.server.gateway.service.dto.CommentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AggregatorService {
    private final ArticleClient articleClient;
    private final CommentClient commentClient;

    public Mono<AggregateResponse> aggregate(Long articleId) {
        return Mono.zip(
                this.articleClient.getArticle(articleId),
                this.commentClient.getComments(articleId)
        ).map(t -> toDto(t.getT1(), t.getT2()));
    }

    private AggregateResponse toDto(ArticleResponse articleResponse, List<CommentResponse> commentResponses) {
        return AggregateResponse
                .create(articleResponse.getId(), articleResponse.getDescription(), commentResponses);
    }
}
