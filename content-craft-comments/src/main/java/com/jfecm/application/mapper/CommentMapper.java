package com.jfecm.application.mapper;

import com.jfecm.domain.dto.requests.CommentCreateRequest;
import com.jfecm.domain.dto.response.CommentCreateResponse;
import com.jfecm.domain.dto.response.CommentListResponse;
import com.jfecm.infrastructure.adapter.out.persistence.entity.CommentEntity;

import java.time.LocalDateTime;

public class CommentMapper {

    private CommentMapper() {

    }

    public static CommentEntity createRequestToEntity(CommentCreateRequest comment) {
        return new CommentEntity(
                null,
                comment.author(),
                comment.text(),
                LocalDateTime.now());
    }

    public static CommentCreateResponse entityToCreateResponse(CommentEntity commentEntity) {
        return new CommentCreateResponse(
                commentEntity.getAuthor(),
                commentEntity.getText(),
                commentEntity.getDate()
        );
    }

    public static CommentListResponse entityToListResponse(CommentEntity commentEntity) {
        return new CommentListResponse(
                commentEntity.getId(),
                commentEntity.getAuthor(),
                commentEntity.getText(),
                commentEntity.getDate()
        );
    }
}
