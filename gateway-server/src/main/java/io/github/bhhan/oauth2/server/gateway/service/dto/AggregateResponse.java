package io.github.bhhan.oauth2.server.gateway.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor(staticName = "create")
public class AggregateResponse {
    private Long id;
    private String description;
    private List<CommentResponse> comments;
}
