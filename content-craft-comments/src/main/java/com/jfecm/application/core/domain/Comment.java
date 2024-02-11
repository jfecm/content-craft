package com.jfecm.application.core.domain;

import java.time.LocalDateTime;

public record Comment (
        String id,
        String author,
        String text,
        LocalDateTime date
) {
}
