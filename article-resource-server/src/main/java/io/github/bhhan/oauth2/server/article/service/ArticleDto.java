package io.github.bhhan.oauth2.server.article.service;

import io.github.bhhan.oauth2.server.article.domain.Article;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

public class ArticleDto {
    @Getter
    @Setter
    @NoArgsConstructor
    public static class Request {
        private String description;

        public Article toEntity(){
            return new Article(null, this.description, LocalDateTime.now());
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Response {
        private Long id;
        private String description;
        private LocalDateTime publishedAt;

        public Response(Article article) {
            this.id = article.getId();
            this.description = article.getDescription();
            this.publishedAt = article.getPublishedAt();
        }
    }
}
