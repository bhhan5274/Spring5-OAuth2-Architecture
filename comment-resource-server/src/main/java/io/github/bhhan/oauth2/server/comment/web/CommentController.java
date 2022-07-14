package io.github.bhhan.oauth2.server.comment.web;

import io.github.bhhan.oauth2.server.comment.service.CommentDto;
import io.github.bhhan.oauth2.server.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PreAuthorize("hasAuthority('SCOPE_message.write')")
    @GetMapping("/{articleId}")
    public List<CommentDto.Response> getCommentsByArticleId(@PathVariable Long articleId){
        return commentService.getCommentsByArticleId(articleId);
    }

    @PreAuthorize("hasAuthority('SCOPE_message.read')")
    @GetMapping("/scope")
    public String scopeRead() {
        return "Hello World";
    }

    @PreAuthorize("hasRole('user')")
    @PostMapping
    public CommentDto.Response addComment(@RequestBody CommentDto.Request request) {
        return commentService.addComment(request);
    }

    @DeleteMapping("/{commentId}")
    public void deleteCommentById(@PathVariable Long commentId, @AuthenticationPrincipal Jwt jwt) {
        CommentDto.Response commentById = commentService.getCommentById(commentId);

        if(scopeContains(jwt, "message.write")){
            commentService.deleteCommentById(commentId);
        }else {
            if(!commentById.getUsername().equals(jwt.getSubject())){
                throw new IllegalArgumentException();
            }
            commentService.deleteCommentById(commentId);
        }
    }

    @SuppressWarnings("unchecked")
    private boolean scopeContains(Jwt jwt, String... args){
        List<String> scopes = (List<String>) jwt.getClaims().get("scope");
        for (String arg : args) {
            if(scopes.contains(arg)){
                return true;
            }
        }
        return false;
    }
}
