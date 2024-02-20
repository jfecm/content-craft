package com.jfecm.model;

import java.time.LocalDateTime;

public record Post (
        Long id,
        String author,
        String title,
        LocalDateTime datePost
) {
}
