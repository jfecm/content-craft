package com.jfecm.domain.dto.response;

import java.time.LocalDateTime;

public record CommentListResponse(
        String id,
        String author,
        String text,
        LocalDateTime date
) {
}
