package io.github.bhhan.oauth2.server.gateway.service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ArticleResponse {
    private Long id;
    private String description;
}
