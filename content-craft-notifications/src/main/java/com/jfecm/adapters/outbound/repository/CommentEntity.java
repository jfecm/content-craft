package com.jfecm.adapters.outbound.repository;

import lombok.*;

import java.time.LocalDateTime;

@ToString
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentEntity {
    private String id;
    private String author;
    private String text;
    private LocalDateTime date;
}