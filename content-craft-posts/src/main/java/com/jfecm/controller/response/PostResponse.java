package com.jfecm.controller.response;

import java.time.LocalDateTime;

public record PostResponse(
        Long id,

        String author,

        String title,

        LocalDateTime datePost) {
}
