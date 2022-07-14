package io.github.bhhan.oauth2.server.comment.service;

import io.github.bhhan.oauth2.server.comment.domain.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    @Transactional(readOnly = true)
    public List<CommentDto.Response> getCommentsByArticleId(Long articleId) {
        return this.commentRepository.findCommentsByArticleId(articleId)
                .stream()
                .map(CommentDto.Response::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CommentDto.Response getCommentById(Long commentId) {
        return new CommentDto.Response(this.commentRepository.findById(commentId)
                .orElseThrow(IllegalArgumentException::new));
    }

    @Transactional
    public CommentDto.Response addComment(CommentDto.Request request){
        return new CommentDto.Response(this.commentRepository.save(request.toEntity()));
    }

    @Transactional
    public void deleteCommentById(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
