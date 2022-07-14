package io.github.bhhan.oauth2.server.comment.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long articleId;
    private String username;
    private String description;

    public Comment(Long id, Long articleId, String username, String description) {
        this.id = id;
        this.articleId = articleId;
        this.username = username;
        this.description = description;
    }
}
