package com.jfecm.model;

import lombok.*;

import java.time.LocalDateTime;

@ToString
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    private Long id;
    private String author;
    private String title;
    private LocalDateTime datePost;
}