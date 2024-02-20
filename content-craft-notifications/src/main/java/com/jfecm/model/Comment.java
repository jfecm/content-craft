package com.jfecm.model;

import java.time.LocalDateTime;

public record Comment (
        String id,
        String author,
        String text,
        LocalDateTime date
) {
}
