package io.github.bhhan.oauth2.server.gateway.service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentResponse {
    private Long articleId;
    private String username;
    private String description;
}
