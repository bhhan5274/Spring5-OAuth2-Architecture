package io.github.bhhan.oauth2.server.comment.service;

import io.github.bhhan.oauth2.server.comment.domain.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class CommentDto {
    @Getter
    @Setter
    @NoArgsConstructor
    public static class Request {
        private Long articleId;
        private String username;
        private String description;

        public Comment toEntity(){
            return new Comment(null, articleId, username, description);
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Response {
        private Long id;
        private Long articleId;
        private String username;
        private String description;

        public Response(Comment comment) {
            this.id = comment.getId();
            this.articleId = comment.getArticleId();
            this.username = comment.getUsername();
            this.description = comment.getDescription();
        }
    }
}
