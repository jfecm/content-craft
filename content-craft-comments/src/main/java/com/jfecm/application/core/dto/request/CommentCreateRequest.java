package com.jfecm.application.core.dto.request;

public record CommentCreateRequest(
        String author,
        String text
) {
}
