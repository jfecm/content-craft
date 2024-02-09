package com.jfecm.domain.dto.requests;

public record CommentCreateRequest(
        String author,
        String text
) {
}
