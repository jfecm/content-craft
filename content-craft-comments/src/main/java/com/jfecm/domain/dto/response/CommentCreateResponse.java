package com.jfecm.domain.dto.response;

import java.time.LocalDateTime;

public record CommentCreateResponse (
        String author,
        String text,
        LocalDateTime date
){
}
