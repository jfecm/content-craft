package com.jfecm.domain.model;

import java.time.LocalDateTime;

public record Comment (
        String id,
        String author,
        String text,
        LocalDateTime date
) {
}
