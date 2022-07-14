package io.github.bhhan.oauth2.server.gateway.web;

import io.github.bhhan.oauth2.server.gateway.service.AggregatorService;
import io.github.bhhan.oauth2.server.gateway.service.dto.AggregateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1/gateway")
@RequiredArgsConstructor
public class GatewayController {
    private final AggregatorService aggregatorService;

    @GetMapping
    public Mono<String> hello(){
        return Mono.just("Hi")
                .flatMap(s -> Mono.deferContextual(ctx -> Mono.just(String.format("%s %s", s, ctx.get("Authorization")))));
    }

    @GetMapping("/{articleId}")
    public Mono<AggregateResponse> getAggregateResponse(@PathVariable Long articleId) {
        return aggregatorService.aggregate(articleId);
    }
}
