package com.jfecm.application.core.dto.response;

import java.time.LocalDateTime;

public record CommentCreateResponse (
        String author,
        String text,
        LocalDateTime date
){
}
