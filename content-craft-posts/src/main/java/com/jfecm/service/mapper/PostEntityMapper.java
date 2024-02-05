package com.jfecm.service.mapper;

import com.jfecm.controller.request.PostRequestCreate;
import com.jfecm.controller.response.PostResponse;
import com.jfecm.model.Post;

import java.time.LocalDateTime;

public class PostEntityMapper {

    private PostEntityMapper() {

    }

    public static Post toEntity(PostRequestCreate post) {
        return Post.builder()
                .author(post.author())
                .title(post.title())
                .datePost(LocalDateTime.now())
                .build();
    }

    public static PostResponse toResponse(Post post) {
        return new PostResponse(
                post.getId(),
                post.getAuthor(),
                post.getTitle(),
                post.getDatePost());
    }

}
